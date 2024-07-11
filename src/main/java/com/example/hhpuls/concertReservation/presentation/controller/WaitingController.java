package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.TokenFacade;
import com.example.hhpuls.concertReservation.presentation.dto.waiting.GetWaitingInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Waiting", description = "대기열 API")
@RestController
@RequestMapping("/waiting")
@RequiredArgsConstructor
public class WaitingController {

    private final TokenFacade tokenFacade;

    @Operation(summary = "대기열 조회", description = "대기열 조회, 토큰이 없으면 생성 및 반환")
    @PostMapping("")
    public GetWaitingInfoDto.Response getWaitingInfo(@RequestBody GetWaitingInfoDto.Request request) {

        return GetWaitingInfoDto.Response.fromCommand(
                this.tokenFacade.getWaitingInfo(request.toCommand())
                );
    }
}
