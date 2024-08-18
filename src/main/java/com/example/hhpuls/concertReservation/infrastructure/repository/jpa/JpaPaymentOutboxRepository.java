package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaPaymentOutboxRepository extends JpaRepository<PaymentOutbox, String> {

    @Modifying
    @Query("UPDATE PaymentOutbox p SET p.outboxStatus = 1 WHERE p.id=:id")
    void updateStatus(@Param("id") String id);

    @Query("SELECT p FROM PaymentOutbox p WHERE p.outboxStatus =:status AND p.createDate > :nowPlusFiveMin")
    List<PaymentOutbox> findAllByOutboxStatusAndCreateDate(Integer status, LocalDateTime nowPlusFiveMin);

    List<PaymentOutbox> findAllByOutboxStatus(Integer outboxStatus);
}
