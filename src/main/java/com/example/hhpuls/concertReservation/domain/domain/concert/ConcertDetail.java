package com.example.hhpuls.concertReservation.domain.domain.concert;

import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "concert_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ConcertDetail extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_date")
    private LocalDateTime concertDate;

    @Column(name = "available_reservation_date")
    private LocalDateTime availableReservationDate;

    @ManyToOne
    @JoinColumn(name = "concert_id", insertable = false, updatable = false)
    private Concert concert;

}
