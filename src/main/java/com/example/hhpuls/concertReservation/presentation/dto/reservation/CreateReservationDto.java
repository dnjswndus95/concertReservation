package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ReservationInfo;
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
        public ReservationCommand.CreateCommand toCommand() {
            return ReservationCommand.CreateCommand.builder()
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

        public static CreateReservationDto.Response from(ReservationInfo reservationInfo) {
            return Response.builder()
                    .seatInfo(SeatInfoDto.from(reservationInfo.seat()))
                    .paymentInfo(PaymentInfoDto.from(reservationInfo.payment()))
                    .concertInfo(ConcertInfoWithCreateDateDto.from(reservationInfo.concertDetail()))
                    .reservationInfo(ReservationInfoDto.from(reservationInfo.reservation()))
                    .build();
        }
    }
}
