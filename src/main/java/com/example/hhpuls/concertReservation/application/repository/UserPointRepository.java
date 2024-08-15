package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserPointRepository {

    public Optional<UserPoint> find(Long userId);

    @Transactional
    public UserPoint save(UserPoint userPoint);

    public Optional<UserPoint> findByIdWithOptimisticLock(Long userId);
}
