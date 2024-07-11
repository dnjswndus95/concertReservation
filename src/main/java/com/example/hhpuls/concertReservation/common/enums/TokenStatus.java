package com.example.hhpuls.concertReservation.common.enums;
public enum TokenStatus {

    WAITING(0),
    ACTIVE(1),
    EXPIRE(2);

    private final Integer value;

    TokenStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
