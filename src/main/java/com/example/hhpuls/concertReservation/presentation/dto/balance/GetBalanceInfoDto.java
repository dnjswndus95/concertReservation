package com.example.hhpuls.concertReservation.presentation.dto.balance;

import lombok.Builder;

public class GetBalanceInfoDto {

    @Builder
    public record Response(
            Long userId,
            Integer balance
    ) {}
}
