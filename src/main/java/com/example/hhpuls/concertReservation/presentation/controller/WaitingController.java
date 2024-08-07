package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.TokenFacade;
import com.example.hhpuls.concertReservation.presentation.dto.waiting.GetWaitingQueueInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// Interceptor로 분리하여 컨트롤러 필요없음.

@Tag(name = "Waiting", description = "대기열 API")
@RestController
@RequestMapping("/waiting")
@RequiredArgsConstructor
public class WaitingController {

    private final TokenFacade tokenFacade;

    @Operation(summary = "대기열 순위 조회", description = "대기열에 등록되어있으면 순위 반환, 없으면 대기열 큐에 삽입 후 순위 반환")
    @GetMapping("/{userId}")
    public GetWaitingQueueInfoDto.Response getWaitingQueueInfo(@PathVariable Long userId) {
        return GetWaitingQueueInfoDto.Response.from(
                this.tokenFacade.getWaitingQueueInfo(userId)
        );
    }
}
