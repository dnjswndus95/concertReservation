package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.presentation.dto.concert.ConcertInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.concert.ConcertInfoWithCreateDateDto;
import com.example.hhpuls.concertReservation.presentation.dto.concert.SeatInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentInfoWithPaymentDateDto;
import com.example.hhpuls.concertReservation.presentation.dto.reservation.CancelReservationDto;
import com.example.hhpuls.concertReservation.presentation.dto.reservation.CreateReservationDto;
import com.example.hhpuls.concertReservation.presentation.dto.reservation.GetUserReservationListDto;
import com.example.hhpuls.concertReservation.presentation.dto.reservation.ReservationInfoDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @PostMapping("/")
    public CreateReservationDto.Response createReservation(@RequestBody CreateReservationDto.Request request) {
        return CreateReservationDto.Response.builder()
                .concertInfo(
                        ConcertInfoWithCreateDateDto.builder()
                                .concertId(1L)
                                .concertCreateDate(LocalDateTime.now())
                                .concertDate(LocalDateTime.now())
                                .concertDetailId(1L)
                                .concertName("콘서트")
                                .build())
                .paymentInfo(
                        PaymentInfoDto.builder()
                                .paymentId(1L)
                                .paymentPrice(10000)
                                .paymentStatus(1)
                                .build())
                .seatInfo(
                        SeatInfoDto.builder()
                                .seatId(1L)
                                .seatNumber(1)
                                .seatPrice(10000)
                                .build())
                .build();
    }

    @PostMapping("/cancel")
    public CancelReservationDto.Response cancelReservation(@RequestBody CancelReservationDto.Request request) {
        return CancelReservationDto.Response.builder()
                .isSuccess(true)
                .build();
    }

    @GetMapping("/{userId}/list")
    public GetUserReservationListDto.Response getUserReservationList(@PathVariable Long userId) {
        List<ReservationInfoDto> reservationInfoList = new ArrayList<>();
        reservationInfoList.add(
                ReservationInfoDto.builder()
                        .concertInfoDto(
                                ConcertInfoDto.builder()
                                        .concertId(1L)
                                        .concertName("콘서트")
                                        .concertDetailId(1L)
                                        .concertDate(LocalDateTime.now())
                                        .build()
                        )
                        .paymentInfo(
                                PaymentInfoWithPaymentDateDto.builder()
                                        .paymentId(1L)
                                        .paymentStatus(1)
                                        .paymentPrice(10000)
                                        .paymentDate(LocalDateTime.now())
                                        .build()
                        )
                        .seatInfo(
                                SeatInfoDto.builder()
                                        .seatId(1L)
                                        .seatNumber(1)
                                        .seatPrice(10000)
                                        .build()
                        )
                        .build()
        );

        return GetUserReservationListDto.Response.builder()
                .reservationInfoList(reservationInfoList)
                .build();
    }


}
