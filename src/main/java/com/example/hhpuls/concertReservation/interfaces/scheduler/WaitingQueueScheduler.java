package com.example.hhpuls.concertReservation.interfaces.scheduler;

import com.example.hhpuls.concertReservation.application.constants.WaitingConstants;
import com.example.hhpuls.concertReservation.application.service.WaitingQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class WaitingQueueScheduler {

    private final WaitingQueueService waitingQueueService;
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void addUserActiveQueue() {
        long availableActiveUserCount = WaitingConstants.ACTIVE_QUEUE_MAX_SIZE - this.waitingQueueService.getActiveUserCount();
        log.info("[대기열 스케줄러] 현재 활성화 큐에 있는 유저 수 : {}", this.waitingQueueService.getActiveUserCount());

        // 활성화 큐 최대 사이즈에서 빈 만큼 대기 큐에서 pop
        List<String> poppedIdList = this.waitingQueueService.getWaitingUserIdListToActive(availableActiveUserCount);

        // 활성화
        for (String userId : poppedIdList) {
            log.info("[대기열 스케줄러] 활성화 큐에 add할 유저 ID : {}", userId);
            this.waitingQueueService.active(Long.parseLong(userId));
        }
    }
}
