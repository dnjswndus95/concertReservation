package com.example.hhpuls.concertReservation.common.handler;

import com.example.hhpuls.concertReservation.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ce) {
        log.info("CustomException : {}", ce.getMessage());
        return ResponseEntity.status(200).body(new ErrorResponse(ce.getCode(), ce.getMessage()));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage());
        return ResponseEntity.status(500).body(new ErrorResponse(500, e.getMessage()));
    }
}
