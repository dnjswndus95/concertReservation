package com.example.hhpuls.concertReservation.infrastructure.kafka.payment;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.domain.domain.payment.message.PaymentMessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaMessageSender implements PaymentMessageSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void send(PaymentEvent event) {
        kafkaTemplate.send("payment-topic", event);
    }
}
