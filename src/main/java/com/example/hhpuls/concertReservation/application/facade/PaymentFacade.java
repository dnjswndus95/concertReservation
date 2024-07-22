package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.service.PaymentService;
import com.example.hhpuls.concertReservation.application.service.TokenService;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final TokenService tokenService;

    @Transactional
    public Payment payment(PaymentCommand.PaymentDoneCommand command) {
        Payment payment = this.paymentService.payment(command);
        this.tokenService.expire(command.userId());
        return payment;
    }
}
