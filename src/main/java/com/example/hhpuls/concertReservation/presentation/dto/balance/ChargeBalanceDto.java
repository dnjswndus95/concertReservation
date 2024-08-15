package com.example.hhpuls.concertReservation.presentation.dto.balance;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import lombok.Builder;

public class ChargeBalanceDto {

    @Builder
    public record Request(
            Long userId,
            Integer balance
    ) {

        public PointCommand.ChargePointCommand toCommand() {
            return new PointCommand.ChargePointCommand(this.userId, this.balance);
        }
    }

    @Builder
    public record Response(
            Boolean isSuccess,
            Integer balance
    ) {
        public static ChargeBalanceDto.Response fromCommand(UserPoint userPoint) {
            return Response.builder()
                    .isSuccess(true)
                    .balance(userPoint.getPoint())
                    .build();
        }
    }
}
