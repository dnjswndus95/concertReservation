package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.ReservationFacade;
import com.example.hhpuls.concertReservation.presentation.dto.reservation.CancelReservationDto;
import com.example.hhpuls.concertReservation.presentation.dto.reservation.CreateReservationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Reservation", description = "예약 API")
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationFacade reservationFacade;
    @Operation(summary = "예약", description = "request Body 내용에 해당하는 예약을 생성합니다.")
    @PostMapping("/")
    public CreateReservationDto.Response createReservation(@RequestBody CreateReservationDto.Request request) {
        return CreateReservationDto.Response.from(
                this.reservationFacade.reserve(request.toCommand())
        );
    }

    @Operation(summary = "예약 취소", description = "Request Body에 해당하는 예약을 취소합니다.")
    @GetMapping("/cancel/{reservationId}")
    public CancelReservationDto.Response cancelReservation(@PathVariable Long reservationId) {
        return CancelReservationDto.Response.from(
                this.reservationFacade.cancel(reservationId)
        );
    }
}
