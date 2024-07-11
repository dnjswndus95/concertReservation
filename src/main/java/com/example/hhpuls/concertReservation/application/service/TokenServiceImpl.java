package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.repository.UserTokenRepository;
import com.example.hhpuls.concertReservation.common.enums.TokenStatus;
import com.example.hhpuls.concertReservation.common.exception.TokenException;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TokenServiceImpl implements TokenService {

    private final UserTokenRepository userTokenRepository;

    private static Integer WAITING_QUEUE_SIZE = 50;

    @Override
    public UserToken expireToken(Long userId, String token) {
        // 토큰 조회
        UserToken findUserToken = this.findToken(userId, token);

        // 토큰 상태 만료
        findUserToken.expireToken();

        // 저장 후 return
        return this.userTokenRepository.save(findUserToken);
    }

    @Override
    public UserToken activeToken(Long userId, String token) {
        // 토큰 조회
        UserToken findUserToken = this.findToken(userId, token);

        // 토큰 상태 활성화
        findUserToken.activeToken();

        // 저장 후 return
        return this.userTokenRepository.save(findUserToken);
    }

    @Override
    public UserToken findToken(Long userId, String token) {
        return this.userTokenRepository.findUserToken(userId, token).orElseThrow(
                () -> new TokenException("유저의 토큰 정보가 존재하지 않습니다.")
        );
    }
    @Override
    public UserToken createToken(Long userId){
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

    public Integer getActiveTokenCount() {
        return this.userTokenRepository.getActiveTokenCount();
    }
}



