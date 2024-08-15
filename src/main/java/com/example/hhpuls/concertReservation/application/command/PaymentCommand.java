package com.example.hhpuls.concertReservation.application.command;

import lombok.Builder;

public class PaymentCommand {

    @Builder
    public record PaymentDoneCommand(
            Long userId,
            Long paymentId,
            Integer point
    ) {}
}
