package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

import java.util.List;

public class GetConcertSeatDto {

    @Builder
    public record Response(
            List<ConcertSeatInfoDto> concertSeatInfoList
    ) {}
}