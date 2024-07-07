package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertInfoDto(
        Long concertId,
        String concertName,
        LocalDateTime concertDate,
        Long concertDetailId
) {}
