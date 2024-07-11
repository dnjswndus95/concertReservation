package com.example.hhpuls.concertReservation.common.enums;

public enum SeatStatus {
    PENDING(0),
    PROGRESS(1),
    CONFIRM(2);

    private final Integer value;

    SeatStatus(Integer value) { this.value = value; }

    public Integer getValue() { return this.value; }
}
