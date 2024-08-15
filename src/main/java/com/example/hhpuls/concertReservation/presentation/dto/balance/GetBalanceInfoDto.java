package com.example.hhpuls.concertReservation.presentation.dto.balance;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class GetBalanceInfoDto {
    @Builder
    public record Response(
            @Schema(description = "유저 PK")
            Long userId,
            @Schema(description = "유저 잔액")
            Integer balance
    ) {

        public static GetBalanceInfoDto.Response fromCommand(UserPoint userPoint) {
            return Response.builder()
                    .userId(userPoint.getUserId())
                    .balance(userPoint.getPoint())
                    .build();
        }
    }
}
