/*
package com.example.hhpuls.concertReservation.unit_test.service;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.repository.PaymentRepository;
import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.application.service.PaymentService;
import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceUnitTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    UserPointRepository userPointRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Test
    @DisplayName("결제 내역 생성 성공")
    public void 결제내역생성_성공() {
        // given
        Payment mockPayment = new Payment(1L, 1L, 1000, PaymentStatus.WAITING.getValue());
        when(paymentRepository.create(any(Payment.class))).thenReturn(mockPayment);

        // when
        Payment payment = paymentService.create(1L, 1000);

        // then
        Assertions.assertThat(payment.getReservationId()).isEqualTo(mockPayment.getReservationId());
        Assertions.assertThat(payment.getPaymentPrice()).isEqualTo(mockPayment.getPaymentPrice());
    }

    @Test
    @DisplayName("결제 성공")
    public void 결제성공() {
        // given
        Long paymentId = 1L;
        Long userId = 2L;
        Integer balance = 3000;
        Long reservationId = 3L;

        PaymentCommand.PaymentDoneCommand command = PaymentCommand.PaymentDoneCommand.builder()
                .paymentId(paymentId)
                .userId(userId)
                .balance(balance)
                .build();

        Payment mockPayment = new Payment(paymentId, reservationId, balance, PaymentStatus.WAITING.getValue());
        UserPoint mockUserPoint = new UserPoint(1L, userId, 5000);
        Reservation mockReservation = new Reservation(reservationId, userId, 1L, 1L, ReservationStatus.PROCESS.getValue());

        given(paymentRepository.findById(paymentId)).willReturn(Optional.of(mockPayment));
        given(userPointRepository.find(userId)).willReturn(Optional.of(mockUserPoint));
        given(reservationRepository.findById(reservationId)).willReturn(Optional.of(mockReservation));

        // when
        PaymentCommand.PaymentDoneCommandResult result = paymentService.payment(command);

        // then
        Assertions.assertThat(result.isSuccess()).isEqualTo(true);
        Assertions.assertThat(result.paymentInfo().paymentStatus()).isEqualTo(PaymentStatus.DONE.getValue());
        Assertions.assertThat(result.paymentInfo().paymentPrice()).isEqualTo(balance);
    }

}
*/
