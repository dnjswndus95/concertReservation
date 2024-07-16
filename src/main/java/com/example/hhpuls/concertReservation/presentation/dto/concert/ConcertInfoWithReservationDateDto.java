package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithReservationDateModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertInfoWithReservationDateDto(
        Long concertId,
        String concertName,
        Long concertDetailId,
        LocalDateTime concertDate,
        LocalDateTime reservationDate
){
    public static ConcertInfoWithReservationDateDto from(ConcertInfoWithReservationDateModel model) {
        return ConcertInfoWithReservationDateDto.builder()
                .concertId(model.concertId())
                .concertName(model.concertName())
                .concertDetailId(model.concertDetailId())
                .concertDate(model.concertDate())
                .reservationDate(model.reservationDate())
                .build();
    }
}
