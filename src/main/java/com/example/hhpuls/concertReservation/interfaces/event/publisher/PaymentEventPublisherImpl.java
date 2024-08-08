package com.example.hhpuls.concertReservation.interfaces.event.publisher;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEventPublisher;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisherImpl implements PaymentEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publish(PaymentSuccessEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
