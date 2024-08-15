package com.example.hhpuls.concertReservation.domain.domain.payment.consumer;

import com.example.hhpuls.concertReservation.application.service.PointService;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final PointService pointService;

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    @Transactional
    public void handleEvent(PaymentEvent paymentEvent) {
        try {
            pointService.usePoint(paymentEvent.getUserId(), paymentEvent.getPrice());
        } catch (Exception e) {
            log.error("꾸에에에에에에에에에에에에에에에엑");
            log.error(e.getMessage());
        }
    }
}
