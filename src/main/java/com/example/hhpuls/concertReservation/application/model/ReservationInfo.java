package com.example.hhpuls.concertReservation.application.model;

import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.Builder;

@Builder
public record ReservationInfo(
        Reservation reservation,
        ConcertDetail concertDetail,
        Seat seat,
        Payment payment
) {}
