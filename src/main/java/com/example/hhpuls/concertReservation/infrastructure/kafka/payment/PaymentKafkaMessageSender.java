package com.example.hhpuls.concertReservation.infrastructure.kafka.payment;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.domain.domain.payment.message.PaymentMessageSender;
import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaMessageSender implements PaymentMessageSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void send(KafkaMessage<PaymentEvent> message) {
        kafkaTemplate.send("payment-topic", message);
    }
}
