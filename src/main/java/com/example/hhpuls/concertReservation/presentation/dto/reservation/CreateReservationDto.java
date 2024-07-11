package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.presentation.dto.concert.ConcertInfoWithCreateDateDto;
import com.example.hhpuls.concertReservation.presentation.dto.concert.SeatInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentInfoDto;
import lombok.Builder;

public class CreateReservationDto {

    @Builder
    public record Request(
            Long userId,
            Long concertDetailId,
            Long seatId
    ) {
        public ReservationCommand.createCommand toCommand() {
            return ReservationCommand.createCommand.builder()
                    .userId(this.userId)
                    .concertDetailId(this.concertDetailId)
                    .seatId(this.seatId)
                    .build();
        }
    }

    @Builder
    public record Response(
            ConcertInfoWithCreateDateDto concertInfo,
            SeatInfoDto seatInfo,
            PaymentInfoDto paymentInfo,

            ReservationInfoDto reservationInfo

    ) {

        public static CreateReservationDto.Response fromCommand(ReservationCommand.createResultCommand command) {
            return Response.builder()
                    .seatInfo(SeatInfoDto.from(command.seatInfo()))
                    .paymentInfo(PaymentInfoDto.from(command.paymentInfo()))
                    .concertInfo(ConcertInfoWithCreateDateDto.from(command.concertInfo()))
                    .reservationInfo(ReservationInfoDto.from(command.reservationInfoModel()))
                    .build();
        }
    }
}
