package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
import lombok.Builder;

@Builder
public record SeatInfoDto(
        Long seatId,
        Integer seatNumber,
        Integer seatPrice
) {
    public static SeatInfoDto from(SeatInfoModel model) {
        return SeatInfoDto.builder()
                .seatId(model.seatId())
                .seatPrice(model.seatPrice())
                .seatNumber(model.seatNumber())
                .build();
    }
}
