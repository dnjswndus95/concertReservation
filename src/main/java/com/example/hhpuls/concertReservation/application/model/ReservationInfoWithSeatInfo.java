package com.example.hhpuls.concertReservation.application.model;

import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import lombok.Builder;

@Builder
public record ReservationInfoWithSeatInfo(
        Reservation reservation,
        Seat seat
        ) {}
