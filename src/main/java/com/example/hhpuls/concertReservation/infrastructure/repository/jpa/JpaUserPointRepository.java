package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserPointRepository extends JpaRepository<UserPoint, Long> {

    public Optional<UserPoint> findByUserId(Long userId);
}
