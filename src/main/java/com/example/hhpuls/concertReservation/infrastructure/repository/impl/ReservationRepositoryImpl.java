package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository jpaReservationRepository;
    @Override
    public Reservation save(Reservation reservation) {
        return this.jpaReservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return this.jpaReservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findUserReservationList(Long userId) {
        return this.jpaReservationRepository.findAllByUserId(userId);
    }

    @Override
    public void resetTemporaryReservation(LocalDateTime nowMinusFiveMinutes) {
        this.jpaReservationRepository.resetTemporaryReservation(nowMinusFiveMinutes);
    }
}
