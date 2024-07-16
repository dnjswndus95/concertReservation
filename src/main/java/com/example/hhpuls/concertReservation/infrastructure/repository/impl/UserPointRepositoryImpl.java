package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaUserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {

    private final JpaUserPointRepository jpaUserPointRepository;
    @Override
    public Optional<UserPoint> find(Long userId) {
        return jpaUserPointRepository.findByUserId(userId);
    }

    @Override
    public UserPoint save(UserPoint userPoint) {
        return this.jpaUserPointRepository.save(userPoint);
    }

}
