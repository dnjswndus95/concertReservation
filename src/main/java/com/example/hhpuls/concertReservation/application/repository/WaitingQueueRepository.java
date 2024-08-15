package com.example.hhpuls.concertReservation.application.repository;

import java.util.List;

public interface WaitingQueueRepository {

    Long addWaitingQueue(Long userId);

    Long addActiveQueue(Long userId);

    Long getWaitingQueueSize();

    Long getActiveQueueSize();

    Long getWaitingQueueRank(Long userId);

    Long getActiveQueueRank(Long userId);

    List<String> popWaitingQueue(long number);

    void popActiveQueue(Long userId);
}
