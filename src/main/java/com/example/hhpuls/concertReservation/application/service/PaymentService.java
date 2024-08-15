package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.repository.*;
import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentLog;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEventPublisher;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.domain.domain.payment.message.PaymentMessageSender;
import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final WaitingQueueService waitingQueueService;
    private final PaymentLogRepository paymentLogRepository;
    private final PaymentEventPublisher paymentEventPublisher;
    private final PaymentMessageSender paymentMessageSender;

    public Payment create(Long reservationId, Integer price, Long userId) {
        Payment payment = new Payment(null, reservationId, price, PaymentStatus.WAITING.getValue());

        try {
            Payment createdPayment = this.paymentRepository.create(payment);
            this.paymentLogRepository.save(new PaymentLog(null, null, createdPayment.getId(), userId, createdPayment.getStatus()));
            return createdPayment;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorCode.PAYMENT_INFO_CREATE_ERROR);
        }
    }

    public Payment payment(PaymentCommand.PaymentDoneCommand command) throws InterruptedException {
        Payment findPayment = this.paymentRepository.findById(command.paymentId()).orElseThrow(
                () -> new CustomException(ErrorCode.PAYMENT_INFO_NOT_FOUND)
        );

        Reservation reservation = this.reservationRepository.findById(findPayment.getReservationId()).orElseThrow(
                () -> new CustomException(ErrorCode.RESERVATION_INFO_NOT_FOUND)
        );

        Seat findSeat = this.seatRepository.findById(reservation.getSeatId()).orElseThrow(
                () -> new CustomException(ErrorCode.SEAT_INFO_NOT_FOUND)
        );

        // 활성화 큐에서 사용자 제거
        this.waitingQueueService.expire(command.userId());

        // 좌석 예약완료상태로 업데이트
        findSeat.updateSeatStatus(SeatStatus.CONFIRM.getValue());

        // 유저 포인트 차감
        this.paymentMessageSender.send(new PaymentEvent(command.userId(), findPayment.getId(), findPayment.getPaymentPrice()));

        // 결제내역 결제완료 상태로 업데이트
        findPayment.done();
        // 예약 예약완료 상태로 업데이트
        reservation.done();

        // 예약 로그 생성
        //this.paymentLogRepository.save(new PaymentLog(null, null, findPayment.getId(), command.userId(), PaymentStatus.DONE.getValue()));

        // event
        //this.paymentEventPublisher.publish(new PaymentEvent(command.userId(), findPayment.getId(), findPayment.getPaymentPrice()));

        return findPayment;
    }
}
