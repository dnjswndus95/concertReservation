package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
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

        public static GetConcertSeatDto.Response fromCommand(ConcertCommand.GetSeatInfoResultCommand command) {
            List<SeatInfoDto> seatList = new ArrayList<>();

            for (SeatInfoModel seatInfoModel : command.seatList()) {
                seatList.add(
                        SeatInfoDto.from(seatInfoModel)
                );
            }

            return Response.builder()
                    .concertId(command.concertId())
                    .concertName(command.concertName())
                    .concertDetailId(command.concertDetailId())
                    .concertDate(command.concertDate())
                    .seatList(seatList)
                    .build();
        }
    }
}