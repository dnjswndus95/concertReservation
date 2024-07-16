package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.BalanceCommand;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.springframework.stereotype.Component;

@Component
public interface BalanceService {

    public UserPoint chargePoint(BalanceCommand.ChargeBalanceCommand chargeCommand);
    public UserPoint findUserPoint(Long userId);
}
