package com.example.hhpuls.concertReservation.domain.domain.payment.event;

import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;

public interface PaymentEventPublisher {

    public void publish(KafkaMessage<PaymentEvent> event);
}
