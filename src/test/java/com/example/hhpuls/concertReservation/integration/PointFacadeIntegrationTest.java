/*
package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.facade.PointFacade;
import com.example.hhpuls.concertReservation.application.service.PointService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/sql/data-init.sql")
public class PointFacadeIntegrationTest {

    @Autowired
    PointFacade pointFacade;

    @Autowired
    PointService pointService;

    @DisplayName("유저가 포인트를 조회한다.")
    @Test
    void 유저는_자신의_포인트_조회를_할_수_있다() {
        // given
        Long userId = 1L;

        // when
        PointCommand.findBalanceResultCommand result = pointFacade.findUserPoint(userId);

        // then
        Assertions.assertThat(result.userId()).isEqualTo(1L);
        Assertions.assertThat(result.balance()).isEqualTo(2000);
    }

    @DisplayName("유저가 포인트를 충전한다.")
    @Test
    void 유저는_포인트를_충전할_수_있다() {
        // given
        PointCommand.ChargeBalanceCommand chargeCommand = PointCommand.ChargeBalanceCommand.builder()
                .userId(1L)
                .balance(3000)
                .build();

        // when
        PointCommand.ChargeBalanceResultCommand result = pointFacade.chargePoint(chargeCommand);

        // then
        Assertions.assertThat(result.balance()).isEqualTo(5000);
        Assertions.assertThat(result.isSuccess()).isEqualTo(true);
    }
}
*/
