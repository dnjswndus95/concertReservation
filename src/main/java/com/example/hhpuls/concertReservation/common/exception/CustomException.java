package com.example.hhpuls.concertReservation.common.exception;

import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import org.springframework.boot.logging.LogLevel;

public class CustomException extends RuntimeException {
    private final Integer code;
    private final String message;
    private final LogLevel logLevel;

    public CustomException(ErrorCode errorCode, LogLevel logLevel) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.logLevel = logLevel;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public LogLevel getLogLevel() { return this.logLevel; }
}
