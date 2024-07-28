package com.example.hhpuls.concertReservation.unit_test.domain;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointDomainTest {

    @Test
    public void 포인트_충전_테스트() {
        // given
        UserPoint userPoint = new UserPoint(1L, 1L, 1000, 0);

        // when
        userPoint.charge(2000);

        // then
        Assertions.assertThat(userPoint.getPoint()).isEqualTo(3000);
    }

    @Test
    public void 포인트_음수_충전_테스트() {
        // given
        UserPoint userPoint = new UserPoint(1L, 1L, 1000, 0);

        // when
        Exception e = assertThrows(Exception.class, () -> userPoint.charge(-2000));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("포인트 충전량은 양수여야만 합니다.");
    }

    @Test
    public void 포인트_사용을_할_수_있다() {
        // given
        UserPoint userPoint = new UserPoint(1L, 1L, 3000, 0);

        // when
        userPoint.use(2000);

        // then
        Assertions.assertThat(userPoint.getPoint()).isEqualTo(1000);
    }

    @Test
    public void 가진_양_보다_많은_포인트를_사용할_수_없다() {
        // given
        UserPoint userPoint = new UserPoint(1L, 1L, 3000, 0);

        // when
        Exception e = assertThrows(Exception.class, () -> userPoint.use(4000));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("가진 양보다 많은 포인트를 사용할 수 없습니다.");
    }

}
