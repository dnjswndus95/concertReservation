package com.example.hhpuls.concertReservation.common.handler;

public record ErrorResponse(
        Integer code,
        String message
) {
}