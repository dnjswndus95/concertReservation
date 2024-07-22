package com.example.hhpuls.concertReservation.domain.domain;

import com.example.hhpuls.concertReservation.common.enums.ReservationStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
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
            throw new CustomException(ErrorCode.RESERVATION_UPDATE_CANCEL_STATUS_FAIL);

        this.status = ReservationStatus.CANCEL.getValue();
    }

    public void done() {
        if(ReservationStatus.PROCESS.getValue() != this.status)
            throw new CustomException(ErrorCode.RESERVATION_UPDATE_DONE_STATUS_FAIL);

        this.status = ReservationStatus.DONE.getValue();
    }
}
