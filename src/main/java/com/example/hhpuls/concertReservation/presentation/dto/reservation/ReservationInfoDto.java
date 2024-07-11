package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.application.model.ReservationInfoModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationInfoDto(
        Long reservationId,
        Integer reservationStatus,
        LocalDateTime createDate
) {
    public static ReservationInfoDto from(ReservationInfoModel model) {
        return ReservationInfoDto.builder()
                .reservationId(model.reservationId())
                .reservationStatus(model.reservationStatus())
                .createDate(model.createDate())
                .build();
    }
}
