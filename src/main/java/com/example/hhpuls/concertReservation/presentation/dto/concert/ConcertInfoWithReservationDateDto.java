package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertInfoWithReservationDateDto(
        Long concertId,
        String concertName,
        Long concertDetailId,
        LocalDateTime reservationDate,
        LocalDateTime concertDate

){
    public static ConcertInfoWithReservationDateDto from(ConcertDetail concertDetail) {
        return ConcertInfoWithReservationDateDto.builder()
                .concertId(concertDetail.getConcertId())
                .concertName(concertDetail.getConcert().getName())
                .concertDetailId(concertDetail.getId())
                .concertDate(concertDetail.getConcertDate())
                .reservationDate(concertDetail.getAvailableReservationDate())
                .build();
    }

}
