package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithCreateDateModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertInfoWithCreateDateDto(
        Long concertId,
        String concertName,
        LocalDateTime concertCreateDate,
        Long concertDetailId,
        LocalDateTime concertDate
) {
    public static ConcertInfoWithCreateDateDto from(ConcertInfoWithCreateDateModel model) {
        return ConcertInfoWithCreateDateDto.builder()
                .concertId(model.concertId())
                .concertDetailId(model.concertDetailId())
                .concertName(model.concertName())
                .concertCreateDate(model.concertCreateDate())
                .concertDate(model.concertDate())
                .build();
    }
}
