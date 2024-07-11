package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.service.PaymentService;
import com.example.hhpuls.concertReservation.application.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final TokenService tokenService;

    @Transactional
    public PaymentCommand.PaymentDoneCommandResult payment(PaymentCommand.PaymentDoneCommand command) {
        this.tokenService.expireToken(command.userId(), command.token());
        try {
            return this.paymentService.payment(command);
        } catch (Exception e) {
            return PaymentCommand.PaymentDoneCommandResult.builder()
                    .isSuccess(false)
                    .build();
        }
    }
}
