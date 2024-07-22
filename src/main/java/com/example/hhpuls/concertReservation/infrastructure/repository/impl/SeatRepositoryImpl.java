package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

    private final JpaSeatRepository jpaSeatRepository;
    @Override
    public Integer countBySeatStatus(Long concertDetailId, Integer seatStatus) {
        return this.jpaSeatRepository.countBySeatStatus(concertDetailId, seatStatus);
    }

    @Override
    public Integer countAll(Long concertDetailId) {
        return this.jpaSeatRepository.countByConcertDetailId(concertDetailId);
    }

    @Override
    public List<Seat> findByConcertDetailId(Long concertDetailId, Integer pendingSeatStatus) {
        return this.jpaSeatRepository.findByConcertDetailIdAndStatus(concertDetailId, pendingSeatStatus);
    }

    @Override
    public Optional<Seat> findById(Long seatId) {
        return this.jpaSeatRepository.findById(seatId);
    }

    @Override
    public void resetTemporarySeats(LocalDateTime nowMinusFiveMinutes) {
        this.jpaSeatRepository.resetTemporarySeats(nowMinusFiveMinutes);
    }
}
