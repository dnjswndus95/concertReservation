package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.repository.PaymentRepository;
import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;
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
    private final UserPointRepository userPointRepository;
    private final ReservationRepository reservationRepository;

    public Payment create(Long reservationId, Integer price) {
        Payment payment = new Payment(null, reservationId, price, PaymentStatus.WAITING.getValue());

        try {
            return this.paymentRepository.create(payment);
        } catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(ErrorCode.PAYMENT_INFO_CREATE_ERROR);
        }
    }

    public Payment payment(PaymentCommand.PaymentDoneCommand command) {
        Payment findPayment = this.paymentRepository.findById(command.paymentId()).orElseThrow(
                () -> new CustomException(ErrorCode.PAYMENT_INFO_NOT_FOUND)
        );

        UserPoint findUserPoint = this.userPointRepository.find(command.userId()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_POINT_NOT_FOUND)
        );

        Reservation reservation = this.reservationRepository.findById(findPayment.getReservationId()).orElseThrow(
                () -> new CustomException(ErrorCode.RESERVATION_INFO_NOT_FOUND)
        );

        // 유저 포인트 차감
        findUserPoint.use(command.point());
        // 결제내역 결제완료 상태로 업데이트
        findPayment.done();
        // 예약 예약완료 상태로 업데이트
        reservation.done();


        return findPayment;
    }
}
