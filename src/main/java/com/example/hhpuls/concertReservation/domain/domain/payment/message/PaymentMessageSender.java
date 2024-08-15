package com.example.hhpuls.concertReservation.domain.domain.payment.message;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import org.springframework.stereotype.Component;

@Component
public interface PaymentMessageSender {
    public void send(PaymentEvent event);
}
