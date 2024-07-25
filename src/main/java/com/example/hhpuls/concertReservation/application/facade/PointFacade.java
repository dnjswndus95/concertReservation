package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.service.PointService;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;

    public UserPoint chargePoint(PointCommand.ChargePointCommand chargeCommand) {
        return this.pointService.chargePoint(chargeCommand);
    }

    public UserPoint getUserPoint(Long userId) {
        return this.pointService.getUserPoint(userId);
    }
}
