package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.PointFacade;
import com.example.hhpuls.concertReservation.presentation.dto.balance.ChargeBalanceDto;
import com.example.hhpuls.concertReservation.presentation.dto.balance.GetBalanceInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Balance", description = "유저 잔액 API")
@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class PointController {

    private final PointFacade pointFacade;

    @Operation(summary = "유저 잔액 조회", description = "userId에 해당하는 유저의 잔액을 조회합니다.")
    @GetMapping("/{userId}")
    public GetBalanceInfoDto.Response getUserPoint(@PathVariable Long userId) {
        return GetBalanceInfoDto.Response.fromCommand(
                this.pointFacade.getUserPoint(userId)
        );
    }

    @Operation(summary = "유저 잔액 충전", description = "userId에 해당하는 유저의 잔액을 충전합니다.")
    @PostMapping("/charge")
    public ChargeBalanceDto.Response chargePoint(@RequestBody ChargeBalanceDto.Request request) {
        return ChargeBalanceDto.Response.fromCommand(
                this.pointFacade.chargePoint(request.toCommand())
        );
    }
}
