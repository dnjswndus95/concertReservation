package com.example.hhpuls.concertReservation.presentation.dto.concert;

import lombok.Builder;

@Builder
public record SeatInfoDto(
        Long seatId,
        Integer seatNumber,
        Integer seatPrice
) {
}
