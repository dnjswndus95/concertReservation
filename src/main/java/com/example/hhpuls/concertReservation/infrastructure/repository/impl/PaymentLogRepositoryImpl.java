package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.PaymentLogRepository;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentLog;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaPaymentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PaymentLogRepositoryImpl implements PaymentLogRepository {

    private final JpaPaymentLogRepository jpaPaymentLogRepository;
    @Override
    public List<PaymentLog> getUserPaymentLogs(Long userId) {
        return jpaPaymentLogRepository.findByUserId(userId);
    }
    @Override
    public PaymentLog save(PaymentLog paymentLog) {
        return jpaPaymentLogRepository.save(paymentLog);
    }
}
