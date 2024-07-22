package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import lombok.Builder;

@Builder
public record SeatInfoDto(
        Long seatId,
        Integer seatNumber,
        Integer seatPrice
) {
    public static SeatInfoDto from(Seat seat) {
        return SeatInfoDto.builder()
                .seatId(seat.getId())
                .seatPrice(seat.getPrice())
                .seatNumber(seat.getPrice())
                .build();
    }
}
