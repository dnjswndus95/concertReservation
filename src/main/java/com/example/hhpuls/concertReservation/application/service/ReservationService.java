package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoWithSeatInfo;
import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public ReservationInfoWithSeatInfo reserve(ReservationCommand.CreateCommand command) {
        Reservation reservation = new Reservation(null, command.userId(), command.concertDetailId(), command.seatId(), ReservationStatus.PROCESS.getValue());
        Seat seat = this.seatRepository.findWithSeatForUpdate(command.seatId()).orElseThrow(
                () -> new CustomException(ErrorCode.SEAT_INFO_NOT_FOUND)
        );

        // 예약 생성
        Reservation savedReservation = this.reservationRepository.save(reservation);
        // 좌석 상태값 변경
        seat.updateSeatStatus(SeatStatus.PROGRESS.getValue());

        return ReservationInfoWithSeatInfo.builder()
                .reservation(savedReservation)
                .seat(seat)
                .build();
    }

    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.RESERVATION_INFO_NOT_FOUND)
        );
    }

    public Reservation cancelReserve(Long id) {
        Reservation reservation = this.findById(id);
        Seat seat = this.seatRepository.findById(reservation.getSeatId()).orElseThrow(
                () -> new CustomException(ErrorCode.SEAT_INFO_NOT_FOUND)
        );

        // 예약 취소상태로 변경
        reservation.cancel();


        // 좌석 예약 가능 상태로 변경
        seat.updateSeatStatus(SeatStatus.PENDING.getValue());

        return reservation;
    }
}
