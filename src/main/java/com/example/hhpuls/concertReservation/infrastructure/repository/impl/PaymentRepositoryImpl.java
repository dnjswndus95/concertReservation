package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.PaymentRepository;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository jpaPaymentRepository;

    @Override
    public Payment create(Payment payment) {
        return this.jpaPaymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(Long paymentId) {
        return this.jpaPaymentRepository.findById(paymentId);
    }
}
