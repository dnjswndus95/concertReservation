package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.TokenFacade;
import com.example.hhpuls.concertReservation.presentation.dto.waiting.GetTokenInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// Interceptor로 분리하여 컨트롤러 필요없음.

@Tag(name = "Waiting", description = "대기열 API")
@RestController
@RequestMapping("/waiting")
@RequiredArgsConstructor
public class TokenController {

    private final TokenFacade tokenFacade;

    /*@Operation(summary = "대기열 조회", description = "대기열 조회, 토큰이 없으면 생성 및 반환")
    @PostMapping("")
    public GetWaitingInfoDto.Response getWaitingInfo(@RequestBody GetWaitingInfoDto.Request request) {

        return GetWaitingInfoDto.Response.fromCommand(
                this.tokenFacade.getWaitingInfo(request.toCommand())
                );
    }*/

    @Operation(summary = "토큰 발급", description = "대기열 토큰 발급")
    @PostMapping("/{userId}")
    public GetTokenInfoDto.Response addWaitingToken(@PathVariable Long userId) {
        return GetTokenInfoDto.Response.from(
                this.tokenFacade.createToken(userId)
        );
    }
}
