package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.BalanceCommand;
import com.example.hhpuls.concertReservation.application.service.BalanceService;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BalanceFacade {

    private final BalanceService balanceService;

    @Transactional
    public BalanceCommand.ChargeBalanceResultCommand chargePoint(BalanceCommand.ChargeBalanceCommand chargeCommand) {
        UserPoint userPoint = this.balanceService.chargePoint(chargeCommand);

        return new BalanceCommand.ChargeBalanceResultCommand(true, userPoint.getPoint());
    }

    public BalanceCommand.findBalanceResultCommand findUserPoint(Long userId) {
        UserPoint findUserPoint = this.balanceService.findUserPoint(userId);

        return new BalanceCommand.findBalanceResultCommand(findUserPoint.getUserId(), findUserPoint.getPoint());
    }

}
