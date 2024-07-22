package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationInfoDto(
        Long reservationId,
        Integer reservationStatus,
        LocalDateTime createDate
) {
    public static ReservationInfoDto from(Reservation reservation) {
        return ReservationInfoDto.builder()
                .reservationId(reservation.getId())
                .reservationStatus(reservation.getStatus())
                .createDate(reservation.getCreateDate())
                .build();
    }
}
