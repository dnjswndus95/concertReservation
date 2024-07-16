package com.example.hhpuls.concertReservation.application.command;

import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithCreateDateModel;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithReservationDateModel;
import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ConcertCommand {

    @Builder
    public record GetConcertInfoListResultCommand(
           List<ConcertInfoWithCreateDateModel> concertInfoList
    ){}

    @Builder
    public record GetConcertDetailResultCommand(
            Long concertId,
            String concertName,
            Long concertDetailId,
            LocalDateTime concertDate,
            LocalDateTime reservationDate,
            LocalDateTime createDate,
            Integer totalSeatCount,

            Integer availableReserveSeatCount
    ) {}

    @Builder
    public record GetAvailableReserveConcertListResultCommand(
            List<ConcertInfoWithReservationDateModel> concertInfoList
    ) {}

    @Builder
    public record GetSeatInfoResultCommand(
            Long concertId,
            String concertName,
            Long concertDetailId,
            LocalDateTime concertDate,

            List<SeatInfoModel> seatList
    ) {}
}
