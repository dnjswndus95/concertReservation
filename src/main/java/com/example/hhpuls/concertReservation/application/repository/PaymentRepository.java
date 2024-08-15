package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;

import java.util.Optional;

public interface PaymentRepository {

    public Payment create(Payment payment);

    public Optional<Payment> findById(Long paymentId);
}
