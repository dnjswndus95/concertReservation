package com.example.hhpuls.concertReservation.interfaces.event.listener;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.domain.domain.payment.message.PaymentMessageSender;
import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentKafkaEventListener {

    private final PaymentMessageSender paymentMessageSender;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void sendMessage(KafkaMessage<PaymentEvent> message) {
        paymentMessageSender.send(message);
    }
}
