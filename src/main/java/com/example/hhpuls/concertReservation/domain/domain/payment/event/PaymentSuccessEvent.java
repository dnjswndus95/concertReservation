package com.example.hhpuls.concertReservation.domain.domain.payment.event;


public class PaymentSuccessEvent {
    private Long userId;
    private Long paymentId;
    private Integer price;

    public PaymentSuccessEvent(Long userId, Long paymentId, Integer price) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.price = price;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Integer getPrice() {
        return this.price;
    }

}
