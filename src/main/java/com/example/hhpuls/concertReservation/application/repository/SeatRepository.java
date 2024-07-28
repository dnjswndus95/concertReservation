package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SeatRepository {

    public Integer countBySeatStatus(Long concertDetailId, Integer seatStatus);
    public Integer countAll(Long id);

    public List<Seat> findByConcertDetailId(Long concertDetailId, Integer pendingSeatStatus);

    public Optional<Seat> findById(Long seatId);

    public void resetTemporarySeats(LocalDateTime nowMinusFiveMinutes);

    public Optional<Seat> findWithSeatForUpdate(Long seatId);
}
