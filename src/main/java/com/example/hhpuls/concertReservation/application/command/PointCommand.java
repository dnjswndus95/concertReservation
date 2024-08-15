package com.example.hhpuls.concertReservation.application.command;

import lombok.Builder;

public class PointCommand {

    @Builder
    public record ChargePointCommand(
            Long userId,
            Integer point
    ) {}
}
