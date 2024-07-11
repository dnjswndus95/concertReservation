package com.example.hhpuls.concertReservation.presentation.controller;

import com.example.hhpuls.concertReservation.application.facade.PaymentFacade;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@Tag(name = "Payment", description = "결제 API")
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;
    @Operation(summary = "결제", description = "결제를 진행합니다.")
    @PostMapping("/")
    public PaymentDto.Response payment(@RequestHeader("Authorization") String authorization,
                                       @RequestBody PaymentDto.Request request) {
        return PaymentDto.Response.fromCommand(
                this.paymentFacade.payment(request.toCommand(authorization))
        );
    }
}
