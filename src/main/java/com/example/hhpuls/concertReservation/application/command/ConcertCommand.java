package com.example.hhpuls.concertReservation.application.command;

import lombok.Builder;

import java.time.LocalDateTime;

public class ConcertCommand {

    @Builder
    public record GetConcertDetailResultCommand(
            Long concertId,
            String concertName,
            Long concertDetailId,
            LocalDateTime concertDate,
            LocalDateTime reservationDate,
            LocalDateTime createDate,
            Integer totalSeatCount,
            Integer availableReserveSeatCount
    ) {}

}
