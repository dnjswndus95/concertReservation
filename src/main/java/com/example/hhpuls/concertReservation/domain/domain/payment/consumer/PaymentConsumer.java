package com.example.hhpuls.concertReservation.domain.domain.payment.consumer;

import com.example.hhpuls.concertReservation.application.repository.PaymentOutboxRepository;
import com.example.hhpuls.concertReservation.application.service.PointService;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final PointService pointService;
    private final ObjectMapper objectMapper;
    private final PaymentOutboxRepository paymentOutboxRepository;

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void usePoint(KafkaMessage<?> message) {
        try {
            LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) message.getMessage();
            PaymentEvent paymentEvent = objectMapper.convertValue(map, PaymentEvent.class);
            pointService.usePoint(paymentEvent.getUserId(), paymentEvent.getPrice());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = "payment-topic", groupId = "payment-group1")
    public void completeOutbox(KafkaMessage<?> message) {
        LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) message.getMessage();
        PaymentEvent paymentEvent = objectMapper.convertValue(map, PaymentEvent.class);
        log.info("PUBLISH OutboxId: {}", paymentEvent.getOutBoxId());

        this.paymentOutboxRepository.completeOutbox(paymentEvent.getOutBoxId());
    }
}
