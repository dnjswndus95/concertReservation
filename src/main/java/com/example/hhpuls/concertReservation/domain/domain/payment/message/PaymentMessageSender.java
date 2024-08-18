package com.example.hhpuls.concertReservation.domain.domain.payment.message;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;
import org.springframework.stereotype.Component;

@Component
public interface PaymentMessageSender {
    public void send(KafkaMessage<PaymentEvent> message);
}
