package com.example.hhpuls.concertReservation.domain.domain;

import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.exception.ReservationException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reservation extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "concert_detail_id")
    private Long concertDetailId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "status")
    private Integer status;

    public void cancel() {
        if(ReservationStatus.PROCESS.getValue() != this.status)
            throw new ReservationException("예약 진행 상태가 아닌 예약은 취소할 수 없습니다.");

        this.status = ReservationStatus.CANCEL.getValue();
    }

    public void done() {
        if(ReservationStatus.PROCESS.getValue() != this.status)
            throw new ReservationException("예약 진행 상태가 아닌 예약은 에약완료 할 수 없습니다.");

        this.status = ReservationStatus.DONE.getValue();
    }
}
