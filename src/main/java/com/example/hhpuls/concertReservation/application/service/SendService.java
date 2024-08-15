package com.example.hhpuls.concertReservation.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendService {

    public void send(Long paymentId, Long userId, Integer price) throws InterruptedException {
        // 외부 API를 호출한다고 가정하고 5초간 sleep
        Thread.sleep(5000);
        log.info("카카오톡 외부 API 호출완료!");
        log.info("paymentId: {}, userId: {}, price: {}", paymentId, userId, price);
    }
}
