package com.example.hhpuls.concertReservation.common.enums;

public enum PaymentStatus {

    WAITING(0),
    DONE(1),
    CANCEL(2);

    private final Integer value;

    PaymentStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
