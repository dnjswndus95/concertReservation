package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.application.service.PaymentService;
import com.example.hhpuls.concertReservation.application.service.WaitingQueueService;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final WaitingQueueService waitingQueueService;

    @Transactional
    public Payment payment(PaymentCommand.PaymentDoneCommand command) throws InterruptedException{
        Payment payment = this.paymentService.payment(command);
        //this.waitingQueueService.expire(command.userId());
        return payment;
    }
}
