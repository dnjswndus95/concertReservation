package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.UserTokenRepository;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaUserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserTokenRepositoryImpl implements UserTokenRepository {

    private final JpaUserTokenRepository jpaUserTokenRepository;

    @Override
    public Optional<UserToken> findByToken(String token) {
        return this.jpaUserTokenRepository.findByToken(token);
    }

    @Override
    public Optional<UserToken> findByUserIdAndTokenValue(Long userId, String token) {
        return this.jpaUserTokenRepository.findByUserIdAndToken(userId, token);
    }

    @Override
    public UserToken save(UserToken userToken) {
        return this.jpaUserTokenRepository.save(userToken);
    }

    @Override
    public void updateStatus(Integer beforeStatus, Integer afterStatus, LocalDateTime expirationTime) {
        this.jpaUserTokenRepository.updateUserTokenStatus(beforeStatus, afterStatus, expirationTime);
    }

    @Override
    public Integer getActiveTokenCount() {
        return this.jpaUserTokenRepository.countUserTokenByStatus();
    }

    @Override
    public Optional<UserToken> findByUserId(Long userId) {
        return this.jpaUserTokenRepository.findByUserId(userId);
    }

}
