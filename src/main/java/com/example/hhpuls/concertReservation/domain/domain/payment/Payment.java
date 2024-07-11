package com.example.hhpuls.concertReservation.domain.domain.payment;

import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.exception.PaymentException;
import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Payment extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "payment_price")
    private Integer paymentPrice;

    @Column(name = "status")
    private Integer status;

    public void paymentDone() {
        if(PaymentStatus.WAITING.getValue() != this.status)
            throw new PaymentException("결제완료할 수 없는 상태입니다.");

        this.status = PaymentStatus.DONE.getValue();
    }

    public void paymentCancel() {
        if(PaymentStatus.WAITING.getValue() != this.status)
            throw new PaymentException("결제취소할 수 없는 상태입니다.");

        this.status = PaymentStatus.CANCEL.getValue();
    }
}
