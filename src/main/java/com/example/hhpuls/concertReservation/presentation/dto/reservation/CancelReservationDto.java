package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import lombok.Builder;

@Builder
public class CancelReservationDto {

    public record Request(
            Long userId,
            Long reservationId
    ) {}

    @Builder
    public record Response(
            Boolean isSuccess
    ) {}
}
