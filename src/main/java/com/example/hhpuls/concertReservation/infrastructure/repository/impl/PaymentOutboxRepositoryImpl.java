package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.PaymentOutboxRepository;
import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaPaymentOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PaymentOutboxRepositoryImpl implements PaymentOutboxRepository {

    private final JpaPaymentOutboxRepository jpaPaymentOutboxRepository;

    @Override
    public Optional<PaymentOutbox> findById(String id) {
        return this.jpaPaymentOutboxRepository.findById(id);
    }

    @Override
    public PaymentOutbox save(PaymentOutbox paymentOutbox) {
        return this.jpaPaymentOutboxRepository.save(paymentOutbox);
    }

    @Override
    @Transactional
    public void completeOutbox(String id) {
       this.jpaPaymentOutboxRepository.updateStatus(id);
    }

    @Override
    public List<PaymentOutbox> findOutboxByStatusAndCreateDate(Integer status, LocalDateTime nowPlusFiveMin) {
        return this.jpaPaymentOutboxRepository.findAllByOutboxStatusAndCreateDate(status, nowPlusFiveMin);
    }

    @Override
    public List<PaymentOutbox> findOutBoxByStatus(Integer status) {
        return this.jpaPaymentOutboxRepository.findAllByOutboxStatus(status);
    }
}
