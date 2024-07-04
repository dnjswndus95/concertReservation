package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ConcertSeatInfoDto(
        Long concertId,
        String concertName,
        Long concertDetailId,
        LocalDateTime concertDate,

        List<SeatInfoDto> seatList
) {}
