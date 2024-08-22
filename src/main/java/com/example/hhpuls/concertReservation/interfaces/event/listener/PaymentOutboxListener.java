package com.example.hhpuls.concertReservation.interfaces.event.listener;

import com.example.hhpuls.concertReservation.application.repository.PaymentOutboxRepository;
import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class PaymentOutboxListener {

    private final PaymentOutboxRepository paymentOutboxRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void paymentOutboxEventHandler(PaymentOutbox paymentOutbox) {
        this.paymentOutboxRepository.save(paymentOutbox);
    }
}
