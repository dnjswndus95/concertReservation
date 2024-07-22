package com.example.hhpuls.concertReservation.presentation.dto.payment;

import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.Builder;


@Builder
public record PaymentInfoDto(
        Long paymentId,
        Integer paymentStatus,
        Integer paymentPrice
) {
    public static PaymentInfoDto from(Payment payment) {
        return PaymentInfoDto.builder()
                .paymentId(payment.getId())
                .paymentStatus(payment.getStatus())
                .paymentPrice(payment.getPaymentPrice())
                .build();
    }
}
