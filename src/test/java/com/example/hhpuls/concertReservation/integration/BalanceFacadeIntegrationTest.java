package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.command.BalanceCommand;
import com.example.hhpuls.concertReservation.application.facade.BalanceFacade;
import com.example.hhpuls.concertReservation.application.service.BalanceService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/sql/data-init.sql")
public class BalanceFacadeIntegrationTest {

    @Autowired
    BalanceFacade balanceFacade;

    @Autowired
    BalanceService balanceService;

    @DisplayName("유저가 포인트를 조회한다.")
    @Test
    void 유저는_자신의_포인트_조회를_할_수_있다() {
        // given
        Long userId = 1L;

        // when
        BalanceCommand.findBalanceResultCommand result = balanceFacade.findUserPoint(userId);

        // then
        Assertions.assertThat(result.userId()).isEqualTo(1L);
        Assertions.assertThat(result.balance()).isEqualTo(2000);
    }

    @DisplayName("유저가 포인트를 충전한다.")
    @Test
    void 유저는_포인트를_충전할_수_있다() {
        // given
        BalanceCommand.ChargeBalanceCommand chargeCommand = BalanceCommand.ChargeBalanceCommand.builder()
                .userId(1L)
                .balance(3000)
                .build();

        // when
        BalanceCommand.ChargeBalanceResultCommand result = balanceFacade.chargePoint(chargeCommand);

        // then
        Assertions.assertThat(result.balance()).isEqualTo(5000);
        Assertions.assertThat(result.isSuccess()).isEqualTo(true);
    }
}


/**
 * @SpringBootTest
 * class ReservationUsecaseTest {
 *
 *     @Autowired
 *     ReservationUsecase reservationUsecase;
 *     @Autowired
 *     ReservationService reservationService;
 *     @Autowired
 *     SeatService seatService;
 *
 *     @DisplayName("5분동안 결제가 이루어지지 않은 10개의 예약씩 취소하며, 점유한 좌석도 점유 이전의 상태로 된다.")
 *     @Test
 *     void given_when_then() {
 *         // given
 *         int cancelledReservationCount = 0;
 *         List<Reservation> reservations = reservationService.selectReservationsByReservationStatus(ReservationStatus.CANCELLED);
 *         assertThat(reservations.size()).isEqualTo(cancelledReservationCount);
 *
 *         // when
 *         reservationUsecase.releaseOccupiedSeats();
 *
 *         // then
 *         Awaitility.await()
 *                 .atMost(10, TimeUnit.SECONDS)
 *                 .untilAsserted(() -> {
 *                     int cancelledReservationCountScheduled = 10;
 *                     List<Reservation> cancelled = reservationService.selectReservationsByReservationStatus(ReservationStatus.CANCELLED);
 *                     assertThat(cancelled).isNotEmpty();
 *                     assertThat(cancelled.size()).isEqualTo(cancelledReservationCountScheduled);
 *                 });
 *     }
 *
 *     @DisplayName("예약정보가 주어지면 예약이 이루어지며, 예약한 좌석이 점유된다.")
 *     @Test
 *     void givenReservationData_whenMakeReservation_thenNothingReturn() {
 *         // given
 *         int price = 4000;
 *         Long concertScheduleId = 1L;
 *         List<Long> seats = List.of(1L, 2L, 5L, 8L);
 *         ReservationCommand.Create create = ReservationCommand.Create.of(price, concertScheduleId, seats);
 *
 *         Long userId = 1L;
 *
 *         // when
 *         ReservationCommand.Get reservation = reservationUsecase.makeReservation(create, userId);
 *
 *         // then
 *         assertThat(reservation).isNotNull();
 *         assertThat(reservation.paymentStatus()).isEqualTo(PaymentStatus.NOT_PAID);
 *         assertThat(reservation.reservationStatus()).isEqualTo(ReservationStatus.ACTIVE);
 *
 *         // 저장된 좌석을 찾아 검증한다.
 *         List<SeatCommand.Get> findSeats = seatService.selectSeatsByIds(seats);
 *         assertThat(findSeats).isNotEmpty();
 *         assertThat(findSeats.size()).isEqualTo(seats.size());
 *     }
 *
 * }
 */