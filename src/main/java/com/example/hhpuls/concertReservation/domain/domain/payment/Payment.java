package com.example.hhpuls.concertReservation.domain.domain.payment;

import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
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

    public void done() {
        if(PaymentStatus.WAITING.getValue() != this.status)
            throw new CustomException(ErrorCode.PAYMENT_UPDATE_DONE_STATUS_FAIL);

        this.status = PaymentStatus.DONE.getValue();
    }

    public void cancel() {
        if(PaymentStatus.WAITING.getValue() != this.status)
            throw new CustomException(ErrorCode.PAYMENT_UPDATE_CANCEL_STATUS_FAIL);

        this.status = PaymentStatus.CANCEL.getValue();
    }
}
