package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.presentation.dto.waiting.GetWaitingInfoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/waiting")
public class WaitingController {

    @PostMapping("")
    public GetWaitingInfoDto.Response getWaitingInfo(@RequestBody GetWaitingInfoDto.Request request) {
        return GetWaitingInfoDto.Response.builder()
                .userId(1L)
                .token("토큰값 예시~~")
                .activeDate(LocalDateTime.now())
                .createDate(LocalDateTime.now())
                .tokenStatus(1)
                .build();
    }
}
