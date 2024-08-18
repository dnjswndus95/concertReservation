package com.example.hhpuls.concertReservation.domain.domain.payment.event;


public class PaymentEvent {
    private Long userId;
    private Long paymentId;
    private Integer price;

    private String outBoxId;

    public PaymentEvent(Long userId, Long paymentId, Integer price, String outBoxId) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.price = price;
        this.outBoxId = outBoxId;
    }

    public PaymentEvent() {

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

    public String getOutBoxId() { return this.outBoxId; }
}
