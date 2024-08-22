package com.example.hhpuls.concertReservation.interfaces.scheduler;

import com.example.hhpuls.concertReservation.application.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PaymentOutboxScheduler {

    private final PaymentService paymentService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void retryOutboxPublish() {
        this.paymentService.outboxResend();
    }
}
