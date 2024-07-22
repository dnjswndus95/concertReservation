/*
package com.example.hhpuls.concertReservation.unit_test.service;


import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.application.service.BalanceService;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @InjectMocks
    BalanceService balanceService;

    @Mock
    UserPointRepository userPointRepository;

    @Test
    public void 유저잔액조회() {
        // given
        UserPoint mockUserPoint = new UserPoint(2L, 1L, 2000);
        given(userPointRepository.find(1L)).willReturn(Optional.of(mockUserPoint));

        // when
        UserPoint resultUserPoint = balanceService.findUserPoint(1L);

        // then
        Assertions.assertThat(resultUserPoint.getPoint()).isEqualTo(2000);
    }

    @Test
    public void 유전잔액조회_유저가없는경우() {
        // given
        UserPoint mockUserPoint = new UserPoint(2L, 1L, 2000);

        // when
        CustomException e = assertThrows(CustomException.class, () -> balanceService.findUserPoint(1L));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("유저 포인트 정보가 없습니다.");
    }

    @Test
    public void 유저잔액충전_음수값() {
        // given
        // 음수충전 command
        PointCommand.ChargeBalanceCommand chargeBalanceCommand = new PointCommand.ChargeBalanceCommand(1L, -1000);
        UserPoint mockFindUser = new UserPoint(2L, 1L, 2000);
        given(userPointRepository.find(1L)).willReturn(Optional.of(mockFindUser));

        // when
        CustomException e = assertThrows(CustomException.class, () -> balanceService.chargePoint(chargeBalanceCommand));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("포인트 충전은 양수만 가능합니다.");
    }

    @Test
    public void 유저잔액충전() {
        // given
        // 2000원 있는 유저에게 1000원 충전
        PointCommand.ChargeBalanceCommand chargeBalanceCommand = new PointCommand.ChargeBalanceCommand(1L, 1000);
        UserPoint mockFindUser = new UserPoint(2L, 1L, 2000);
        UserPoint chargedMockUser = new UserPoint(2L, 1L, 3000);
        given(userPointRepository.find(1L)).willReturn(Optional.of(mockFindUser));
        when(userPointRepository.save(any(UserPoint.class))).thenReturn(chargedMockUser);

        // when
        UserPoint userPoint = balanceService.chargePoint(chargeBalanceCommand);

        // then
        Assertions.assertThat(userPoint.getUserId()).isEqualTo(1L);
        Assertions.assertThat(userPoint.getPoint()).isEqualTo(3000);
    }
}*/
