package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.ConcertFacade;
import com.example.hhpuls.concertReservation.presentation.dto.concert.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Concert", description = "콘서트 API")
@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertFacade concertFacade;

    @Operation(summary = "에약 가능한 콘서트 목록 조회", description = "현재 예약 가능한 콘서트 목록을 조회 합니다.")
    @GetMapping("")
    public GetConcertListDto.Response getConcertList() {
        return GetConcertListDto.Response.from(
                this.concertFacade.getConcertInfoList()
        );
    }

    @Operation(summary = "콘서트 정보 조회", description = "concertId에 해당하는 콘서트 정보를 조회합니다.")
    @GetMapping("/detail/{concertDetailId}")
    public GetConcertDetailDto.Response getConcertDetail(@PathVariable Long concertDetailId) {
        return GetConcertDetailDto.Response.from(
                this.concertFacade.getConcertDetailInfo(concertDetailId)
        );
    }

    @Operation(summary = "현재 시점 예약 가능한 콘서트 목록 조회", description = "현재 시점에서 concertId로 된 예약가능한 모든 콘서트 정보를 조회한다.")
    @GetMapping("/{concertId}/date")
    public GetAvailableReserveConcertListDto.Response getConcertAvailableReservationDate(@PathVariable Long concertId) {
        return GetAvailableReserveConcertListDto.Response.from(
                this.concertFacade.getAvailableReserveConcertList(concertId)
        );
    }

    @Operation(summary = "예약 가능한 좌석 조회", description = "해당 날짜에 예약 가능한 좌석을 조회합니다.")
    @GetMapping("/{concertDetailId}/seat")
    public GetConcertSeatDto.Response getConcertSeat(@PathVariable Long concertDetailId) {
        return GetConcertSeatDto.Response.from(
                this.concertFacade.getSeatInfo(concertDetailId)
        );
    }

}
