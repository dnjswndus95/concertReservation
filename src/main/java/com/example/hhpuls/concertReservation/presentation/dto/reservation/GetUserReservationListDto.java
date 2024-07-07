package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import lombok.Builder;

import java.util.List;

public class GetUserReservationListDto {

    @Builder
    public record Response(
            List<ReservationInfoDto> reservationInfoList
    ) {}
}
