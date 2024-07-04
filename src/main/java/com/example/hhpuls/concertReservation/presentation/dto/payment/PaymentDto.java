package com.example.hhpuls.concertReservation.presentation.dto.payment;

import lombok.Builder;

public class PaymentDto {

    @Builder
    public record Request(
            Long userId,
            Long paymentId,
            Integer balance
    ) {}

    @Builder
    public record Response(
            Boolean isSuccess,
            PaymentInfoWithPaymentDateDto paymentInfo
    ) {}
}
