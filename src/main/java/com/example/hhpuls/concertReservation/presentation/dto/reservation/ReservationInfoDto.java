package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.presentation.dto.concert.ConcertInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.concert.SeatInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentInfoWithPaymentDateDto;
import lombok.Builder;

@Builder
public record ReservationInfoDto(
        SeatInfoDto seatInfo,
        PaymentInfoWithPaymentDateDto paymentInfo,
        ConcertInfoDto concertInfoDto
) {
}
