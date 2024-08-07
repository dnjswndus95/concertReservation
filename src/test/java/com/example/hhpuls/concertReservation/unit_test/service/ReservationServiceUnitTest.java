/*
package com.example.hhpuls.concertReservation.unit_test.service;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoWithSeatInfoModel;
import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.application.service.ReservationService;
import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceUnitTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    SeatRepository seatRepository;

    @Test
    @DisplayName("좌석 예약")
    public void 좌석예약() {
        // given
        Long seatId = 1L;
        ReservationCommand.createCommand command = new ReservationCommand.createCommand(1L, 1L, 1L);
        Seat seat = new Seat(seatId, 1L, 1, 1000, SeatStatus.PENDING.getValue());
        Reservation reservation = new Reservation(1L, 1L, 1L, seatId, ReservationStatus.PROCESS.getValue());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        given(seatRepository.findById(seatId)).willReturn(Optional.of(seat));


        // when
        ReservationInfoWithSeatInfoModel reserve = this.reservationService.reserve(command);

        // then
        Assertions.assertThat(reserve.seatInfoModel().seatNumber()).isEqualTo(1L);
        Assertions.assertThat(reserve.reservationInfo().reservationStatus()).isEqualTo(ReservationStatus.PROCESS.getValue());
    }

    @Test
    @DisplayName("좌석 예약 실패")
    public void 좌석예약_실패() {
        // given
        Long seatId = 1L;
        ReservationCommand.createCommand command = new ReservationCommand.createCommand(1L, 1L, 1L);
        Reservation reservation = new Reservation(1L, 1L, 1L, seatId, ReservationStatus.PROCESS.getValue());
        given(seatRepository.findById(seatId)).willReturn(Optional.empty());

        // when
        CustomException e = assertThrows(CustomException.class, () -> reservationService.reserve(command));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("예약하려는 좌석의 정보가 없습니다.");

    }

    @Test
    @DisplayName("좌석 예약 취소")
    public void 좌석예약_취소() {
        // given
        Long reservationId = 1L;
        Long seatId = 2L;
        Reservation reservation = new Reservation(reservationId, 2L, 2L, 2L, ReservationStatus.PROCESS.getValue());
        Seat seat = new Seat(seatId, 1L, 1, 2000, SeatStatus.PROGRESS.getValue());
        given(seatRepository.findById(seatId)).willReturn(Optional.of(seat));
        given(reservationRepository.findById(reservationId)).willReturn(Optional.of(reservation));

        // when
        Reservation result = reservationService.cancelReserve(reservationId);

        // then
        Assertions.assertThat(result.getId()).isEqualTo(1L);
        Assertions.assertThat(result.getStatus()).isEqualTo(ReservationStatus.CANCEL.getValue());

    }

    @Test
    @DisplayName("예약 조회")
    public void 예약조회() {
        // given
        Long reservationId = 1L;

        // when
        CustomException e = assertThrows(CustomException.class, () -> reservationService.findById(reservationId));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("예약 정보를 찾을 수 없습니다.");
    }

}
*/
