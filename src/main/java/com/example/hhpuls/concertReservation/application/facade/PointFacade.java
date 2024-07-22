package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.service.PointService;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;

    @Transactional
    public UserPoint chargePoint(PointCommand.ChargePointCommand chargeCommand) {
        return this.pointService.chargePoint(chargeCommand);
    }

    public UserPoint findUserPoint(Long userId) {
        return this.pointService.findUserPoint(userId);
    }

}
