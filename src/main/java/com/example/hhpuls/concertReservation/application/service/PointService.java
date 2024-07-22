package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointService {

    private final UserPointRepository userPointRepository;

    public UserPoint chargePoint(PointCommand.ChargePointCommand chargeCommand) {
        UserPoint userPoint = this.selectUserPoint(chargeCommand.userId());

        userPoint.charge(chargeCommand.point());

        return this.userPointRepository.save(userPoint);
    }

    public UserPoint findUserPoint(Long userId) {
        return this.selectUserPoint(userId);
    }

    private UserPoint selectUserPoint(Long userId) {
        return this.userPointRepository.find(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_POINT_IS_NOT_FOUND)
        );
    }
}
