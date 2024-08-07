package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ReservationInfo;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoWithSeatInfo;
import com.example.hhpuls.concertReservation.application.service.ConcertService;
import com.example.hhpuls.concertReservation.application.service.PaymentService;
import com.example.hhpuls.concertReservation.application.service.ReservationService;
import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final PaymentService paymentService;

    @Transactional
    public ReservationInfo reserve(ReservationCommand.CreateCommand command) {
        // 콘서트 정보 조회
        ConcertDetail findConcertDetail = this.concertService.findConcertDetail(command.concertDetailId());

        // Reservation 생성
        ReservationInfoWithSeatInfo reservationInfoWithSeatInfo = this.reservationService.reserve(command);

        // 결제내역 생성
        Payment payment = this.paymentService.create(reservationInfoWithSeatInfo.reservation().getId(), reservationInfoWithSeatInfo.seat().getPrice(), command.userId());

        return ReservationInfo.builder()
                .concertDetail(findConcertDetail)
                .payment(payment)
                .seat(reservationInfoWithSeatInfo.seat())
                .reservation(reservationInfoWithSeatInfo.reservation())
                .build();
    }

    @Transactional
    public Reservation cancel(Long reservationId) {
        return this.reservationService.cancelReserve(reservationId);
    }
}
