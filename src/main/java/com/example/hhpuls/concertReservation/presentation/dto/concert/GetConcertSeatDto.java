package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithSeatList;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetConcertSeatDto {

    @Builder
    public record Response(
            Long concertId,
            String concertName,
            Long concertDetailId,
            LocalDateTime concertDate,

            List<SeatInfoDto> seatList
    ) {

        public static GetConcertSeatDto.Response from(ConcertInfoWithSeatList model) {
            List<SeatInfoDto> seatList = new ArrayList<>();

            for (Seat seat : model.seatList()) {
                seatList.add(SeatInfoDto.from(seat));
            }

            return Response.builder()
                    .concertId(model.concertId())
                    .concertName(model.concertName())
                    .concertDetailId(model.concertDetailId())
                    .concertDate(model.concertDate())
                    .seatList(seatList)
                    .build();
        }
    }
}