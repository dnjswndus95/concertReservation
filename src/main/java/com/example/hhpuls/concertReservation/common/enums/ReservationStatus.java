package com.example.hhpuls.concertReservation.common.enums;

public enum ReservationStatus {
    PROCESS(0),
    DONE(1),
    CANCEL(2);

    private final Integer value;

    ReservationStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
