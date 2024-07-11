package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoModel;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoWithSeatInfoModel;
import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
import com.example.hhpuls.concertReservation.application.repository.ReservationRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.ReservationException;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    @Override
    public ReservationInfoWithSeatInfoModel reserve(ReservationCommand.createCommand command) {
        Reservation reservation = new Reservation(null, command.userId(), command.concertDetailId(), command.seatId(), ReservationStatus.PROCESS.getValue());
        Seat seat = this.seatRepository.findById(command.seatId()).orElseThrow(
                () -> new ReservationException("예약하려는 좌석의 정보가 없습니다.")
        );
        // 예약 생성
        Reservation savedReservation = this.reservationRepository.save(reservation);

        // 좌석 상태값 변경
        seat.updateSeatStatus(SeatStatus.PROGRESS.getValue());

        return ReservationInfoWithSeatInfoModel.builder()
                .reservationInfo(ReservationInfoModel.fromDomain(savedReservation))
                .seatInfoModel(SeatInfoModel.fromDomain(seat))
                .build();
    }

    @Override
    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElseThrow(
                () -> new ReservationException("예약 정보를 찾을 수 없습니다.")
        );
    }

    @Override
    public Reservation cancelReserve(Long id) {
        Reservation reservation = this.findById(id);
        Seat seat = this.seatRepository.findById(reservation.getSeatId()).orElseThrow(
                () -> new ReservationException("예약하려는 좌석의 정보가 없습니다.")
        );

        // 예약 취소상태로 변경
        reservation.cancel();


        // 좌석 예약 가능 상태로 변경
        seat.updateSeatStatus(SeatStatus.PENDING.getValue());

        return reservation;
    }
}
