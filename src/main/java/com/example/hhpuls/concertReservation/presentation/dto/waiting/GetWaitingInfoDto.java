package com.example.hhpuls.concertReservation.presentation.dto.waiting;

import lombok.Builder;

import java.time.LocalDateTime;

public class GetWaitingInfoDto {

    @Builder
    public record Request(
            Long userId,
            String token
    ) {}

    @Builder
    public record Response(
            Long userId,
            String token,
            Integer tokenStatus,
            LocalDateTime activeDate,
            LocalDateTime createDate
    ) {}
}
