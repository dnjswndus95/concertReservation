package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import lombok.Builder;

@Builder
public class CancelReservationDto {

    @Builder
    public record Response(
            Boolean isSuccess
    ) {
        public static CancelReservationDto.Response fromCommand(ReservationCommand.cancelResultCommand command) {
            return Response.builder()
                    .isSuccess(command.isSuccess())
                    .build();
        }
    }
}
