package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithCreateDateModel;
import com.example.hhpuls.concertReservation.application.model.PaymentInfoModel;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoWithSeatInfoModel;
import com.example.hhpuls.concertReservation.application.service.ConcertService;
import com.example.hhpuls.concertReservation.application.service.PaymentService;
import com.example.hhpuls.concertReservation.application.service.ReservationService;
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
    public ReservationCommand.createResultCommand reserve(ReservationCommand.createCommand command) {
        // Reservation 생성
        ReservationInfoWithSeatInfoModel reservedInfo = this.reservationService.reserve(command);

        // 콘서트 정보 조회
        ConcertDetail findConcertDetail = this.concertService.findConcertDetail(command.concertDetailId());

        // 결제내역 생성
        Payment payment = this.paymentService.create(reservedInfo.reservationInfo().reservationId(), reservedInfo.seatInfoModel().seatPrice());

        return ReservationCommand.createResultCommand.builder()
                .concertInfo(ConcertInfoWithCreateDateModel.fromDomain(findConcertDetail))
                .seatInfo(reservedInfo.seatInfoModel())
                .paymentInfo(PaymentInfoModel.fromDomain(payment))
                .reservationInfoModel(reservedInfo.reservationInfo())
                .build();
    }

    @Transactional
    public ReservationCommand.cancelResultCommand cancel(Long reservationId) {
        try {
            this.reservationService.cancelReserve(reservationId);
        } catch (Exception e) {
            return ReservationCommand.cancelResultCommand.builder()
                    .isSuccess(false)
                    .build();
        }

        return ReservationCommand.cancelResultCommand.builder()
                .isSuccess(true)
                .build();
    }
}
