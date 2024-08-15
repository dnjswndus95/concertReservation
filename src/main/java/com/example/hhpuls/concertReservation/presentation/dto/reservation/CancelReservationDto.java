package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.domain.domain.reservation.Reservation;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class CancelReservationDto {

    @Builder
    public record Response(
            Long reservationId,
            LocalDateTime cancelDate
    ) {
        public static CancelReservationDto.Response from(Reservation reservation) {
            return Response.builder()
                    .reservationId(reservation.getId())
                    .cancelDate(reservation.getUpdateDate())
                    .build();
        }
    }
}
