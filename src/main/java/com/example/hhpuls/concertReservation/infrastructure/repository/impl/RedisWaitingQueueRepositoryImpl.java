package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.WaitingQueueRepository;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import com.example.hhpuls.concertReservation.interfaces.config.SpringConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.hhpuls.concertReservation.domain.error_code.ErrorCode.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisWaitingQueueRepositoryImpl implements WaitingQueueRepository {

    // Redis의 ZSET과 관련된 연산 집합
    private ZSetOperations<String, String> zSetOperations;

    // Redis와 상호작용
    private final RedisTemplate<String, String> redisTemplate;

    private final SpringConfig springConfig;

    private final String WAITING_KEY = "waiting";
    private final String ACTIVE_KEY = "active";

    @PostConstruct
    private void init() {
        // ZSetOperations 객체 반환
        zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public Long addWaitingQueue(Long userId) {
        String key = this.getWaitingQueueKey();
        Boolean isSuccess = zSetOperations.add(key, userId.toString(), System.currentTimeMillis());
        if(!isSuccess)
            throw new CustomException(ADD_WAITING_QUEUE_FAIL);

        return zSetOperations.rank(key, userId.toString());
    }


    @Override
    public Long addActiveQueue(Long userId) {
        String key = this.getActiveQueueKey(userId);
        Boolean isSuccess = zSetOperations.add(key, userId.toString(), System.currentTimeMillis());

        if(!isSuccess)
            throw new CustomException(ADD_ACTIVE_QUEUE_FAIL);

        // 각 유저의 활성화 큐는 5분의 만료시간을 갖는다.
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);

        return zSetOperations.rank(key, userId.toString());
    }

    @Override
    public Long getWaitingQueueSize() {
        return zSetOperations.size(this.getWaitingQueueKey());
    }

    @Override
    public Long getActiveQueueSize() {
        // 검색 옵션 설정
        ScanOptions scanOptions = ScanOptions.scanOptions().match(this.springConfig.getActiveProfile() + ":" + ACTIVE_KEY + "*").count(100).build();

        // 키 개수를 세기 위한 변수
        long count = 0;

        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(scanOptions)) {
            while (cursor.hasNext()) {
                cursor.next();
                count++;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return count;
    }

    @Override
    public Long getWaitingQueueRank(Long userId) {
        return zSetOperations.rank(this.getWaitingQueueKey(), userId.toString());
    }

    @Override
    public Long getActiveQueueRank(Long userId) {
        return zSetOperations.rank(this.getActiveQueueKey(userId), userId.toString());
    }

    @Override
    public List<String> popWaitingQueue(long number) {
        Set<ZSetOperations.TypedTuple<String>> poppedUserList = zSetOperations.popMin(this.getWaitingQueueKey(), number);

        ArrayList<String> userIdList = new ArrayList<>();

        for (ZSetOperations.TypedTuple<String> user : poppedUserList) {
            userIdList.add(user.getValue());
        }

        return userIdList;
    }

    @Override
    public void popActiveQueue(Long userId) {
        zSetOperations.remove(this.getActiveQueueKey(userId), userId.toString());
    }

    public String getWaitingQueueKey() {
        return this.springConfig.getActiveProfile() + ":" + WAITING_KEY;
    }

    public String getActiveQueueKey(Long userId) {
        return this.springConfig.getActiveProfile() + ":" + ACTIVE_KEY + ":" + userId.toString();
    }
}
