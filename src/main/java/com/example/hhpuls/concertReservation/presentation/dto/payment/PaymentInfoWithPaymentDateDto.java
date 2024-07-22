package com.example.hhpuls.concertReservation.presentation.dto.payment;

import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentInfoWithPaymentDateDto(
        Long paymentId,
        Integer paymentStatus,
        Integer paymentPrice,
        LocalDateTime paymentDate
) {
    public static PaymentInfoWithPaymentDateDto from(Payment payment) {
        return PaymentInfoWithPaymentDateDto.builder()
                .paymentId(payment.getId())
                .paymentPrice(payment.getPaymentPrice())
                .paymentStatus(payment.getStatus())
                .paymentDate(payment.getUpdateDate())
                .build();
    }
}
