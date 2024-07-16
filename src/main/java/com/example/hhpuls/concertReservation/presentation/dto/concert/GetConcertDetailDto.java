package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
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
            Integer totalSeatCount,
            Integer availableReserveSeatCount
            ) {

        public static GetConcertDetailDto.Response fromCommand(ConcertCommand.GetConcertDetailResultCommand command) {
            return Response.builder()
                    .concertId(command.concertId())
                    .concertDetailId(command.concertDetailId())
                    .createDate(command.createDate())
                    .concertDate(command.concertDate())
                    .concertName(command.concertName())
                    .reservationDate(command.reservationDate())
                    .totalSeatCount(command.totalSeatCount())
                    .availableReserveSeatCount(command.availableReserveSeatCount())
                    .build();
        }

    }
}
