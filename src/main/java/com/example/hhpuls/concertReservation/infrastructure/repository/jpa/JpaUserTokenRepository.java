package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JpaUserTokenRepository extends JpaRepository<UserToken, Long> {

    public Optional<UserToken> findByToken(String token);

    public Optional<UserToken> findByUserIdAndToken(Long userId, String token);

    @Transactional
    @Modifying
    @Query("UPDATE UserToken ut SET ut.status = :afterStatus WHERE ut.status = :beforeStatus AND ut.activeDate > :expirationTime")
    public void updateUserTokenStatus(Integer beforeStatus, Integer afterStatus, LocalDateTime expirationTime);

    @Query("SELECT count(ut) FROM UserToken ut WHERE ut.status = 1")
    public Integer countUserTokenByStatus();

    public Optional<UserToken> findByUserId(Long userId);
}
