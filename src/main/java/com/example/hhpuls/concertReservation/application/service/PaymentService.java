package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.PaymentCommand;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;

public interface PaymentService {

    public Payment create(Long reservationId, Integer price);

    public PaymentCommand.PaymentDoneCommandResult payment(PaymentCommand.PaymentDoneCommand command);
}
