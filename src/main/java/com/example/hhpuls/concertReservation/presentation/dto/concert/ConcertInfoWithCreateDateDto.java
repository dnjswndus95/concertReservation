package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
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
    public static ConcertInfoWithCreateDateDto from(ConcertDetail concertDetail) {
        return ConcertInfoWithCreateDateDto.builder()
                .concertId(concertDetail.getConcertId())
                .concertName(concertDetail.getConcert().getName())
                .concertCreateDate(concertDetail.getConcert().getCreateDate())
                .concertDetailId(concertDetail.getId())
                .concertDate(concertDetail.getConcertDate())
                .build();
    }
}
