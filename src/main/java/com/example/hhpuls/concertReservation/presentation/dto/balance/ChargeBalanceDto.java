package com.example.hhpuls.concertReservation.presentation.dto.balance;

import com.example.hhpuls.concertReservation.application.command.BalanceCommand;
import lombok.Builder;

public class ChargeBalanceDto {

    @Builder
    public record Request(
            Long userId,
            Integer balance
    ) {

        public BalanceCommand.ChargeBalanceCommand toCommand() {
            return new BalanceCommand.ChargeBalanceCommand(this.userId, this.balance);
        }
    }

    @Builder
    public record Response(
            Boolean isSuccess,
            Integer balance
    ) {
        public static ChargeBalanceDto.Response fromCommand(BalanceCommand.ChargeBalanceResultCommand resultCommand) {
            return new ChargeBalanceDto.Response(resultCommand.isSuccess(), resultCommand.balance());
        }
    }
}
