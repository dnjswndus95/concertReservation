package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentOutboxRepository {

    public Optional<PaymentOutbox> findById(String id);

    public PaymentOutbox save(PaymentOutbox paymentOutbox);

    public void completeOutbox(String id);

    public List<PaymentOutbox> findOutboxByStatusAndCreateDate(Integer status, LocalDateTime nowPlusFiveMin);

    public List<PaymentOutbox> findOutBoxByStatus(Integer status);
}
