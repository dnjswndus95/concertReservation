package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.BalanceCommand;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.common.exception.UserPointException;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BalanceServiceImpl implements BalanceService {

    private final UserPointRepository userPointRepository;

    @Override
    public UserPoint chargePoint(BalanceCommand.ChargeBalanceCommand chargeCommand) {
        UserPoint userPoint = this.selectUserPoint(chargeCommand.userId());

        userPoint.chargePoint(chargeCommand.balance());

        return this.userPointRepository.save(userPoint);
    }

    @Override
    public UserPoint findUserPoint(Long userId) {
        return this.selectUserPoint(userId);
    }

    private UserPoint selectUserPoint(Long userId) {
        return this.userPointRepository.find(userId).orElseThrow(
                () -> new UserPointException("유저 포인트 정보가 없습니다.")
        );
    }
}
