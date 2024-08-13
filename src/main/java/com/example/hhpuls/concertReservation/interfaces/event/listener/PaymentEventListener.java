package com.example.hhpuls.concertReservation.interfaces.event.listener;

import com.example.hhpuls.concertReservation.application.service.SendService;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEventPublisher;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final SendService sendService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentSuccessHandler(PaymentSuccessEvent event) throws InterruptedException {
        this.sendService.send(event.getPaymentId(), event.getUserId(), event.getPrice());
    }
}
