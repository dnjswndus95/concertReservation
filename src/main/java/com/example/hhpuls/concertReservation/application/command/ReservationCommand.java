package com.example.hhpuls.concertReservation.application.command;

import lombok.Builder;

public class ReservationCommand {

    @Builder
    public record CreateCommand(
            Long userId,
            Long concertDetailId,
            Long seatId
    ) {}
}
