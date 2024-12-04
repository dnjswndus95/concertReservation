package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Slf4j
public class PointService {

    private final UserPointRepository userPointRepository;


    public UserPoint chargePoint(PointCommand.ChargePointCommand chargeCommand) {
        UserPoint userPoint = this.userPointRepository.find(chargeCommand.userId()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_POINT_NOT_FOUND)
        );

        userPoint.charge(chargeCommand.point());

        return userPointRepository.save(userPoint);
    }

    @Transactional
    public UserPoint usePoint(Long userId, Integer point) {
        UserPoint userPoint = this.userPointRepository.find(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_POINT_NOT_FOUND)
        );

        userPoint.use(point);

        return userPoint;
    }

    public UserPoint getUserPoint(Long userId) {
        return this.userPointRepository.find(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_POINT_IS_NOT_FOUND)
        );
    }
}
