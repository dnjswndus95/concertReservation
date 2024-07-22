package com.example.hhpuls.concertReservation.common.exception;

import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;

public class CustomException extends RuntimeException {
    private final Integer code;
    private final String message;

    public CustomException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }
}
