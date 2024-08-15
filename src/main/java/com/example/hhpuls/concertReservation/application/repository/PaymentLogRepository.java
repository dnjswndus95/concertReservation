package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentLog;

import java.util.List;

public interface PaymentLogRepository {

    public List<PaymentLog> getUserPaymentLogs(Long userId);

    public PaymentLog save(PaymentLog paymentLog);

}
