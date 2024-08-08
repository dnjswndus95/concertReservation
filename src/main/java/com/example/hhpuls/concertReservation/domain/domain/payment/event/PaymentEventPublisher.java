package com.example.hhpuls.concertReservation.domain.domain.payment.event;

public interface PaymentEventPublisher {

    public void publish(PaymentSuccessEvent event);
}
