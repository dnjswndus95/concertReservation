package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserPointRepository {

    public Optional<UserPoint> find(Long userId);

    public UserPoint save(UserPoint userPoint);
}
