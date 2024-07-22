package com.example.hhpuls.concertReservation.unit_test.domain;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BalanceDomainTest {

    @Test
    public void 포인트_충전_테스트() {
        // given
        UserPoint userPoint = new UserPoint(1L, 1L, 1000);

        // when
        userPoint.charge(2000);

        // then
        Assertions.assertThat(userPoint.getPoint()).isEqualTo(3000);
    }

    @Test
    public void 포인트_음수_충전_테스트() {
        // given
        UserPoint userPoint = new UserPoint(1L, 1L, 1000);

        // when
        Exception e = assertThrows(Exception.class, () -> userPoint.charge(-2000));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("포인트 충전량은 양수여야만 합니다.");
    }

}
