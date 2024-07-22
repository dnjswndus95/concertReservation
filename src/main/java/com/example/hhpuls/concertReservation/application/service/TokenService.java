package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.repository.UserTokenRepository;
import com.example.hhpuls.concertReservation.common.enums.TokenStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.hhpuls.concertReservation.domain.error_code.ErrorCode.INVALID_TOKEN;

@RequiredArgsConstructor
@Component
public class TokenService {

    private final UserTokenRepository userTokenRepository;

    private static Integer WAITING_QUEUE_SIZE = 30;

    public UserToken expire(Long userId) {
        // 토큰 조회
        UserToken findUserToken = this.find(userId);

        // 토큰 상태 만료
        findUserToken.expireToken();

        // 저장 후 return
        return this.userTokenRepository.save(findUserToken);
    }

    public UserToken active(Long userId) {
        Integer activeTokenCount = this.getActiveTokenCount();

        // 토큰 조회
        UserToken findUserToken = this.find(userId);

        if(activeTokenCount < WAITING_QUEUE_SIZE)


        // 토큰 상태 활성화
        findUserToken.activeToken();

        // 저장 후 return
        return this.userTokenRepository.save(findUserToken);
    }

    public UserToken find(Long userId) {
        return this.userTokenRepository.findByUserId(userId).orElseThrow(
                () -> new CustomException(ErrorCode.TOKEN_NOT_FOUND)
        );
    }

    public UserToken create(Long userId){
        Integer activeTokenCount = this.getActiveTokenCount();

        // 대기열이 꽉 안찼으면 활성화 상태로 토큰 발급 아니라면 대기 상태로 발급
        Integer tokenStatus = WAITING_QUEUE_SIZE > activeTokenCount ? TokenStatus.ACTIVE.getValue() : TokenStatus.WAITING.getValue();

        UserToken userToken = new UserToken(
                null,
                userId,
                UUID.randomUUID().toString(),
                tokenStatus,
                LocalDateTime.now(),
                null);

        return this.userTokenRepository.save(userToken);
    }

    public void valid(String token) {
        UserToken userToken = this.userTokenRepository.findByToken(token).orElseThrow(
                () -> new CustomException(ErrorCode.TOKEN_NOT_FOUND)
        );

        if(userToken.getStatus() != TokenStatus.ACTIVE.getValue())
            throw new CustomException(INVALID_TOKEN);
    }

    public Integer getActiveTokenCount() {
        return this.userTokenRepository.getActiveTokenCount();
    }
}



