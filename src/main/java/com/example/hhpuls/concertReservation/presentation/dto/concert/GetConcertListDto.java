package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

import java.util.List;

public class GetConcertListDto {

    @Builder
    public record Response(
            List<ConcertInfoWithCreateDateDto> concertList
    ) {}
}
