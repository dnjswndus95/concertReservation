package com.example.hhpuls.concertReservation.domain.domain.payment;

import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "payment_outbox")
public class PaymentOutbox extends BaseTime {

    @Id
    private String id;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "outbox_status")
    private Integer outboxStatus;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "price")
    private Integer price;
}
