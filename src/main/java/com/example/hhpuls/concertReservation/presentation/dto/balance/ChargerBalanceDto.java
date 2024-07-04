package com.example.hhpuls.concertReservation.presentation.dto.balance;

import lombok.Builder;

public class ChargerBalanceDto {

    @Builder
    public record Request(
            Long userId,
            Integer balance
    ) {}

    @Builder
    public record Response(
            Boolean isSuccess
    ) {}
}
