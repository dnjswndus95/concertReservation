package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.presentation.dto.balance.ChargerBalanceDto;
import com.example.hhpuls.concertReservation.presentation.dto.balance.GetBalanceInfoDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @GetMapping("/{userId}")
    public GetBalanceInfoDto.Response getBalanceInfo(@PathVariable Long userId) {
        return GetBalanceInfoDto.Response.builder()
                .userId(1L)
                .balance(50000)
                .build();
    }

    @PostMapping("/charge")
    public ChargerBalanceDto.Response chargeBalance(@RequestBody ChargerBalanceDto.Request request) {
        return ChargerBalanceDto.Response.builder()
                .isSuccess(true)
                .build();
    }
}
