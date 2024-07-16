package com.example.hhpuls.concertReservation.presentation.dto.payment;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import lombok.Builder;

public class PaymentDto {

    @Builder
    public record Request(
            Long userId,
            Long paymentId,
            Integer balance
    ) {
        public PaymentCommand.PaymentDoneCommand toCommand(String token) {
            return PaymentCommand.PaymentDoneCommand.builder()
                    .paymentId(this.paymentId)
                    .userId(this.userId)
                    .balance(this.balance)
                    .token(token)
                    .build();
        }
    }

    @Builder
    public record Response(
            Boolean isSuccess,
            PaymentInfoWithPaymentDateDto paymentInfo
    ) {

        public static PaymentDto.Response fromCommand(PaymentCommand.PaymentDoneCommandResult command) {
            return Response.builder()
                    .isSuccess(command.isSuccess())
                    .paymentInfo(PaymentInfoWithPaymentDateDto.from(command.paymentInfo()))
                    .build();
        }
    }
}
