package com.example.hhpuls.concertReservation.presentation.dto.waiting;

import com.example.hhpuls.concertReservation.application.command.WaitingCommand;
import lombok.Builder;

import java.time.LocalDateTime;

public class GetWaitingInfoDto {

    @Builder
    public record Request(
            Long userId,
            String token
    ) {

        public WaitingCommand.GetWaitingInfoCommand toCommand() {
            return new WaitingCommand.GetWaitingInfoCommand(this.userId, this.token);
        }
    }

    @Builder
    public record Response(
            Long userId,
            String token,
            Integer tokenStatus,
            LocalDateTime activeDate,
            LocalDateTime createDate
    ) {
        public static GetWaitingInfoDto.Response fromCommand(WaitingCommand.GetWaitingInfoResultCommand command) {
            return new GetWaitingInfoDto.Response(
                    command.userId(),
                    command.token(),
                    command.tokenStatus(),
                    command.activeDate(),
                    command.createDate()
            );
        }
    }
}
