package com.example.hhpuls.concertReservation.presentation.dto.payment;

import com.example.hhpuls.concertReservation.application.model.PaymentInfoWithCreateDateModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentInfoWithPaymentDateDto(
        Long paymentId,
        Integer paymentStatus,
        Integer paymentPrice,
        LocalDateTime paymentDate
) {
    public static PaymentInfoWithPaymentDateDto from(PaymentInfoWithCreateDateModel model) {
        return PaymentInfoWithPaymentDateDto.builder()
                .paymentId(model.paymentId())
                .paymentPrice(model.paymentPrice())
                .paymentStatus(model.paymentStatus())
                .paymentDate(model.paymentDate())
                .build();
    }
}
