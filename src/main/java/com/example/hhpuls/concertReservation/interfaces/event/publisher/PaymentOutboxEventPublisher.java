package com.example.hhpuls.concertReservation.interfaces.event.publisher;

import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentOutboxEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(PaymentOutbox paymentOutbox) {
        this.applicationEventPublisher.publishEvent(paymentOutbox);
    }
}
