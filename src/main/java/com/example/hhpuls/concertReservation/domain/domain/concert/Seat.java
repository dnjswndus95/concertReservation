package com.example.hhpuls.concertReservation.domain.domain.concert;

import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "seat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Seat extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "concert_detail_id")
    private Long concertDetailId;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private Integer status;

    /*@Version
    private Integer version;*/

    public Integer updateSeatStatus(Integer status) {
        for (SeatStatus seatStatus : SeatStatus.values()) {
            if(status == seatStatus.getValue()) {

                if(this.status == status)
                    throw new IllegalArgumentException("좌석을 같은 상태로 변경 할 수 없습니다.");
                this.status = seatStatus.getValue();
                return this.status;
            }
        }

        throw new IllegalArgumentException("올바르지 않은 좌석 상태값입니다 : " +  status);
    }

}
