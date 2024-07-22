package com.example.hhpuls.concertReservation.presentation.dto.payment;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.Builder;

public class PaymentDto {

    @Builder
    public record Request(
            Long userId,
            Long paymentId,
            Integer point
    ) {
        public PaymentCommand.PaymentDoneCommand toCommand(String token) {
            return PaymentCommand.PaymentDoneCommand.builder()
                    .paymentId(this.paymentId)
                    .userId(this.userId)
                    .point(this.point)
                    .build();
        }
    }

    @Builder
    public record Response(
            PaymentInfoWithPaymentDateDto paymentInfo
    ) {

        public static PaymentDto.Response fromCommand(Payment payment) {
            return Response.builder()
                    .paymentInfo(PaymentInfoWithPaymentDateDto.from(payment))
                    .build();
        }
    }
}
