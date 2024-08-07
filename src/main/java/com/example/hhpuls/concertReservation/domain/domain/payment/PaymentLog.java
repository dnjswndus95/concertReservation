package com.example.hhpuls.concertReservation.domain.domain.payment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private Integer status;
}
