package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.facade.PaymentFacade;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.application.repository.UserTokenRepository;
import com.example.hhpuls.concertReservation.application.service.ReservationService;
import com.example.hhpuls.concertReservation.application.service.TokenService;
import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.enums.TokenStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest
@ActiveProfiles("testdb")
@Sql("/sql/payment-test-data-init.sql")
public class PaymentFacadeIntegrationTest {

    @Autowired
    PaymentFacade paymentFacade;

    @Autowired
    TokenService tokenService;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ReservationService reservationService;


    @Test
    @DisplayName("결제 테스트")
    void 사용자는_좌석을_결제할_수_있다() {
        // given
        Long userId = 1L;
        Long paymentId = 1L;
        Integer point = 20000;

        PaymentCommand.PaymentDoneCommand command = new PaymentCommand.PaymentDoneCommand(userId, paymentId, point);

        // when
        Payment payment = paymentFacade.payment(command);
        Reservation reservation = reservationService.findById(payment.getReservationId());
        UserToken userToken = tokenService.find(userId);
        Seat seat = seatRepository.findById(reservation.getSeatId()).orElseThrow(
                () -> new CustomException(ErrorCode.SEAT_INFO_NOT_FOUND)
        );


        // then
        // 결제 검증
        Assertions.assertThat(payment.getStatus()).isEqualTo(PaymentStatus.DONE.getValue());
        // 예약 검증
        Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.DONE.getValue());
        // 좌석 검증
        Assertions.assertThat(seat.getStatus()).isEqualTo(SeatStatus.CONFIRM.getValue());
        // 토큰 만료 검증
        Assertions.assertThat(userToken.getStatus()).isEqualTo(TokenStatus.EXPIRE.getValue());
    }

    @Test
    @DisplayName("결제 테스트 실패")
    void 사용자는_이미_완료된_결제내역에_대해_결제할_수_없다() {
        // given
        Long userId = 1L;
        Long paymentId = 2L;
        Integer point = 20000;
        PaymentCommand.PaymentDoneCommand command = new PaymentCommand.PaymentDoneCommand(userId, paymentId, point);

        // when
        CustomException e = assertThrows(CustomException.class, () -> paymentFacade.payment(command));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("결제 완료처리에 실패했습니다.");
    }

}
