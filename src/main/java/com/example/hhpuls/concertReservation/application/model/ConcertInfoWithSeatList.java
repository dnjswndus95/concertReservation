package com.example.hhpuls.concertReservation.application.model;

import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ConcertInfoWithSeatList (
        Long concertId,
        String concertName,
        Long concertDetailId,
        LocalDateTime concertDate,
        List<Seat> seatList
){}
