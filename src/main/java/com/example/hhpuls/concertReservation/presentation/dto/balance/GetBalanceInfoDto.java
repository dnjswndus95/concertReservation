package com.example.hhpuls.concertReservation.presentation.dto.balance;

import com.example.hhpuls.concertReservation.application.command.BalanceCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class GetBalanceInfoDto {
    @Builder
    public record Response(
            @Schema(description = "유저 PK")
            Long userId,
            @Schema(description = "유저 잔액")
            Integer balance
    ) {

        public static GetBalanceInfoDto.Response fromCommand(BalanceCommand.findBalanceResultCommand command) {
            return Response.builder()
                    .userId(command.userId())
                    .balance(command.balance())
                    .build();
        }
    }
}
