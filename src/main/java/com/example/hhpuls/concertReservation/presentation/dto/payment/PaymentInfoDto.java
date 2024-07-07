package com.example.hhpuls.concertReservation.presentation.dto.payment;

import lombok.Builder;


@Builder
public record PaymentInfoDto(
        Long paymentId,
        Integer paymentStatus,
        Integer paymentPrice
) {
}
