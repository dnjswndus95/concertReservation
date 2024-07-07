package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

import java.time.LocalDateTime;

public class GetConcertDetailDto {

    @Builder
    public record Response(
            Long concertId,
            String concertName,
            Long concertDetailId,

            LocalDateTime concertDate,
            LocalDateTime reservationDate,
            LocalDateTime createDate,
            Integer seatCount
            ) {}
}
