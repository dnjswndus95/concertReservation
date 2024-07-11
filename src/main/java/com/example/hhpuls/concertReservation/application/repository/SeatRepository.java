package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {

    public Integer countBySeatStatus(Long concertDetailId, Integer seatStatus);
    public Integer countAll(Long id);

    public List<Seat> findByConcertDetailId(Long concertDetailId, Integer pendingSeatStatus);

    public Optional<Seat> findById(Long seatId);


}
