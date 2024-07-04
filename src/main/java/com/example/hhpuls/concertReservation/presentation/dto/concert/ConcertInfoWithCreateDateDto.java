package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertInfoWithCreateDateDto(
        Long concertId,
        String concertName,
        LocalDateTime concertCreateDate,
        Long concertDetailId,
        LocalDateTime concertDate
) {}
