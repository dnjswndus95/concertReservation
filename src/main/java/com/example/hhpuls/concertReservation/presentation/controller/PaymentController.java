package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentDto;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentInfoWithPaymentDateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/")
    public PaymentDto.Response payment(@RequestBody PaymentDto.Request request) {
        return PaymentDto.Response.builder()
                .isSuccess(true)
                .paymentInfo(
                        PaymentInfoWithPaymentDateDto.builder()
                                .paymentId(1L)
                                .paymentDate(LocalDateTime.now())
                                .paymentPrice(10000)
                                .paymentStatus(1)
                                .build()
                )
                .build();
    }
}
