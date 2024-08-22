package com.example.hhpuls.concertReservation.interfaces.event.publisher;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEventPublisher;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.domain.domain.payment.message.PaymentMessageSender;
import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisherImpl implements PaymentEventPublisher {

    private final PaymentMessageSender paymentMessageSender;

    @Override
    public void publish(KafkaMessage<PaymentEvent> event) {
        this.paymentMessageSender.send(event);
    }
}
