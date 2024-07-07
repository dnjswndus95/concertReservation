package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.presentation.dto.concert.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @GetMapping("")
    public GetConcertListDto.Response getConcertList() {

        // 목 데이터 반환
        ConcertInfoWithCreateDateDto listElem1 = ConcertInfoWithCreateDateDto.builder()
                .concertId(1L)
                .concertName("콘서트1")
                .concertCreateDate(LocalDateTime.now())
                .concertDetailId(1L)
                .concertDate(LocalDateTime.now())
                .build();

        ConcertInfoWithCreateDateDto listElem2 = ConcertInfoWithCreateDateDto.builder()
                .concertId(2L)
                .concertName("콘서트2")
                .concertCreateDate(LocalDateTime.now())
                .concertDetailId(2L)
                .concertDate(LocalDateTime.now())
                .build();

        List<ConcertInfoWithCreateDateDto> concertList = new ArrayList<>();
        concertList.add(listElem1);
        concertList.add(listElem2);

        return GetConcertListDto.Response.builder()
                .concertList(concertList)
                .build();
    }

    @GetMapping("/{concertId}")
    public GetConcertDetailDto.Response getConcertDetail(@PathVariable Long concertId) {

        return GetConcertDetailDto.Response.builder()
                .concertId(1L)
                .concertName("콘서트1")
                .concertDetailId(1L)
                .concertDate(LocalDateTime.now())
                .reservationDate(LocalDateTime.now())
                .createDate(LocalDateTime.now())
                .seatCount(50)
                .build();
    }

    @GetMapping("/{concertId}/date")
    public GetConcertAvailableReservationDateListDto.Response getConcertAvailableReservationDate(@PathVariable Long concertId) {
        return GetConcertAvailableReservationDateListDto.Response.builder()
                .concertId(1L)
                .concertName("콘서트1")
                .concertDetailId(1L)
                .concertDate(LocalDateTime.now())
                .reservationDate(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{concertId}/seat")
    public GetConcertSeatDto.Response getConcertSeat(@PathVariable Long concertId,
                                                     @RequestParam LocalDateTime date) {

        SeatInfoDto seatInfoDto1 = SeatInfoDto.builder()
                .seatId(1L)
                .seatNumber(1)
                .seatPrice(10000)
                .build();

        SeatInfoDto seatInfoDto2 = SeatInfoDto.builder()
                .seatId(2L)
                .seatNumber(2)
                .seatPrice(20000)
                .build();

        List<SeatInfoDto> seatInfoDtoList = new ArrayList<>();
        seatInfoDtoList.add(seatInfoDto1);
        seatInfoDtoList.add(seatInfoDto2);

        ConcertSeatInfoDto concertSeatInfoDto = ConcertSeatInfoDto.builder()
                .concertId(1L)
                .concertName("콘서트1")
                .concertDetailId(1L)
                .concertDate(LocalDateTime.now())
                .seatList(seatInfoDtoList)
                .build();

        List<ConcertSeatInfoDto> concertSeatInfoList = new ArrayList<>();
        concertSeatInfoList.add(concertSeatInfoDto);

        return GetConcertSeatDto.Response.builder()
                .concertSeatInfoList(concertSeatInfoList)
                .build();
    }

}
