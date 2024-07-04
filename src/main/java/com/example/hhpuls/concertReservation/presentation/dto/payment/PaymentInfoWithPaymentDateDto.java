package com.example.hhpuls.concertReservation.presentation.dto.payment;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentInfoWithPaymentDateDto(
        Long paymentId,
        Integer paymentStatus,
        Integer paymentPrice,
        LocalDateTime paymentDate
) { }
