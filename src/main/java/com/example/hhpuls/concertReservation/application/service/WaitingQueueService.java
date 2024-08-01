package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.repository.WaitingQueueRepository;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.hhpuls.concertReservation.domain.error_code.ErrorCode.INVALID_TOKEN;

@RequiredArgsConstructor
@Component
public class WaitingQueueService {

    private final WaitingQueueRepository waitingQueueRepository;

    public void expire(Long userId) {
        this.waitingQueueRepository.popActiveQueue(userId);
    }

    public Long active(Long userId) {
        return this.waitingQueueRepository.addActiveQueue(userId);
    }

    public Long getWaitingQueueInfo(Long userId){
        Long waitingQueueRank = this.waitingQueueRepository.getWaitingQueueRank(userId);

        if(waitingQueueRank == null)
            return waitingQueueRepository.addWaitingQueue(userId);

        return waitingQueueRank;
    }

    public void valid(Long userId) {
        Long rank = this.waitingQueueRepository.getActiveQueueRank(userId);

        if(rank == null)
            throw new CustomException(INVALID_TOKEN);
    }

    public Long getActiveUserCount() {
        return this.waitingQueueRepository.getActiveQueueSize();
    }

    public List<String> getWaitingUserIdListToActive(Long num) {
        return this.waitingQueueRepository.popWaitingQueue(num);
    }
}



