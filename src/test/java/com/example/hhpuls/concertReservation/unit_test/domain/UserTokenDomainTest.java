package com.example.hhpuls.concertReservation.unit_test.domain;

import com.example.hhpuls.concertReservation.common.enums.TokenStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTokenDomainTest {

    @Test
    public void 유저토큰만료() {
        // given
        UserToken token = new UserToken(1L, 1L, UUID.randomUUID().toString(), null, null, null);

        // when
        token.expireToken();

        // then
        Assertions.assertThat(token.getStatus()).isEqualTo(TokenStatus.EXPIRE.getValue());
    }

    @Test
    public void 유저토큰활성화() {
        // given
        UserToken token = new UserToken(1L, 1L, UUID.randomUUID().toString(), null, null, null);

        // when
        token.activeToken();

        // then
        Assertions.assertThat(token.getStatus()).isEqualTo(TokenStatus.ACTIVE.getValue());
    }

    @Test
    @DisplayName("이미 활성화인 상태")
    public void 유저토큰활성화_실패1() {
        // given
        UserToken token = new UserToken(1L, 1L, UUID.randomUUID().toString(), TokenStatus.ACTIVE.getValue(), null, null);

        // when
        CustomException e = assertThrows(CustomException.class, () -> token.activeToken());

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 활성화된 토큰입니다.");
    }

    @Test
    @DisplayName("이미 만료된 상태")
    public void 유저토큰활성화_실패2() {
        // given
        UserToken token = new UserToken(1L, 1L, UUID.randomUUID().toString(), TokenStatus.EXPIRE.getValue(), null, null);

        // when
        CustomException e = assertThrows(CustomException.class, () -> token.activeToken());

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 만료된 토큰입니다.");
    }
}
