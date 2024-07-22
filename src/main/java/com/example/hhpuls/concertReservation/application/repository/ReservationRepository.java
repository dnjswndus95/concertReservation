package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ReservationRepository {

    public Reservation save(Reservation reservation);

    public Optional<Reservation> findById(Long id);

    public List<Reservation> findUserReservationList(Long userId);

    public void resetTemporaryReservation(LocalDateTime nowMinusFiveMinutes);
}
