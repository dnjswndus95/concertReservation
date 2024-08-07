package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPaymentLogRepository extends JpaRepository<PaymentLog, Long> {

    public List<PaymentLog> findByUserId(Long userId);
}
