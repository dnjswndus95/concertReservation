package com.example.hhpuls.concertReservation.unit_test.domain;

import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeatDomainUnitTest {

    @Test
    @DisplayName("좌석상태 업데이트")
    public void 좌석상태_업데이트() {
        // given
        Seat seat = new Seat(1L, 1L, 1, 1000, SeatStatus.PENDING.getValue());

        // when
        seat.updateSeatStatus(SeatStatus.PROGRESS.getValue());

        // then
        Assertions.assertThat(seat.getStatus()).isEqualTo(SeatStatus.PROGRESS.getValue());
    }

    @Test
    @DisplayName("같은 좌석상태로 업데이트")
    public void 같은좌석상태로_업데이트() {
        // given
        Seat seat = new Seat(1L, 1L, 1, 1000, SeatStatus.PROGRESS.getValue());

        // when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> seat.updateSeatStatus(SeatStatus.PROGRESS.getValue()));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("좌석을 같은 상태로 변경 할 수 없습니다.");
    }

    @Test
    @DisplayName("유효하지않는 좌석상태로 업데이트")
    public void 유효하지않는_좌석상태로_업데이트() {
        // given
        Seat seat = new Seat(1L, 1L, 1, 1000, SeatStatus.PROGRESS.getValue());
        Integer status = 100;

        // when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> seat.updateSeatStatus(status));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("올바르지 않은 좌석 상태값입니다 : " +  status);
    }
}
