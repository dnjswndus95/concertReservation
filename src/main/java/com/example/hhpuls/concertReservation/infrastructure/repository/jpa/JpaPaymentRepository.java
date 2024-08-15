package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {
}
