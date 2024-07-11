package com.example.hhpuls.concertReservation.unit_test.domain;

import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.exception.ReservationException;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationUnitTest {

    @Test
    @DisplayName("예약취소")
    public void 예약취소() {
        // given
        Reservation reservation = new Reservation(1L, 1L, 1L, 1L, ReservationStatus.PROCESS.getValue());

        // when
        reservation.cancel();

        // then
        Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCEL.getValue());
    }

    @Test
    @DisplayName("예약취소 실패")
    public void 예약취소_실패() {
        // given
        Reservation reservation = new Reservation(1L, 1L, 1L, 1L, ReservationStatus.DONE.getValue());

        // when
        ReservationException e = assertThrows(ReservationException.class, () -> reservation.cancel());

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("예약 진행 상태가 아닌 예약은 취소할 수 없습니다.");
    }

    @Test
    @DisplayName("예약완료")
    public void 예약완료() {
        // given
        Reservation reservation = new Reservation(1L, 1L, 1L, 1L, ReservationStatus.PROCESS.getValue());

        // when
        reservation.done();

        // then
        Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.DONE.getValue());
    }

    @Test
    @DisplayName("예약완료 실패")
    public void 예약완료_실패() {
        // given
        Reservation reservation = new Reservation(1L, 1L, 1L, 1L, ReservationStatus.DONE.getValue());

        // when
        ReservationException e = assertThrows(ReservationException.class, () -> reservation.done());

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("예약 진행 상태가 아닌 예약은 에약완료 할 수 없습니다.");
    }
}
