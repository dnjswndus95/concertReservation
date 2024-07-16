package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.model.PaymentInfoWithCreateDateModel;
import com.example.hhpuls.concertReservation.application.repository.PaymentRepository;
import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.exception.PaymentException;
import com.example.hhpuls.concertReservation.common.exception.ReservationException;
import com.example.hhpuls.concertReservation.common.exception.UserPointException;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserPointRepository userPointRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Payment create(Long reservationId, Integer price) {
        Payment payment = new Payment(null, reservationId, price, PaymentStatus.WAITING.getValue());

        try {
            return this.paymentRepository.create(payment);
        } catch (Exception e){
            log.info(e.getMessage());
            throw new PaymentException("결제 내역 생성에 실패했습니다.");
        }
    }

    @Override
    public PaymentCommand.PaymentDoneCommandResult payment(PaymentCommand.PaymentDoneCommand command) {
        Payment findPayment = this.paymentRepository.findById(command.paymentId()).orElseThrow(
                () -> new PaymentException("결제 정보를 찾지 못했습니다.")
        );

        UserPoint findUserPoint = this.userPointRepository.find(command.userId()).orElseThrow(
                () -> new UserPointException("유저 포인트 정보를 찾지 못했습니다")
        );

        Reservation reservation = this.reservationRepository.findById(findPayment.getReservationId()).orElseThrow(
                () -> new ReservationException("예약 정보를 찾지 못했습니다")
        );

        // 유저 포인트 차감
        findUserPoint.usePoint(command.balance());
        // 결제내역 결제완료 상태로 업데이트
        findPayment.paymentDone();
        // 예약 예약완료 상태로 업데이트
        reservation.done();


        return PaymentCommand.PaymentDoneCommandResult.builder()
                .isSuccess(true)
                .paymentInfo(PaymentInfoWithCreateDateModel.builder()
                        .paymentId(findPayment.getId())
                        .paymentStatus(findPayment.getStatus())
                        .paymentPrice(findPayment.getPaymentPrice())
                        .paymentDate(findPayment.getCreateDate())
                        .build())
                .build();
    }
}
