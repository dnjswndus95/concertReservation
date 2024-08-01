package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.service.WaitingQueueService;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenFacade {

    private final WaitingQueueService waitingQueueService;

    public Long getWaitingQueueInfo(Long userId) {
        return this.waitingQueueService.getWaitingQueueInfo(userId);
    }
}
