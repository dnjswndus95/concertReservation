package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public interface UserTokenRepository {

    Optional<UserToken> findByToken(String token);

    Optional<UserToken> findByUserIdAndTokenValue(Long userId, String token);
    UserToken save(UserToken userToken);

    void updateStatus(Integer beforeStatus, Integer afterStatus, LocalDateTime expirationTime);

    Integer getActiveTokenCount();

    Optional<UserToken> findByUserId(Long userId);
}
