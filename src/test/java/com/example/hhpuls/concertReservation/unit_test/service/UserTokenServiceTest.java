/*
package com.example.hhpuls.concertReservation.unit_test.service;

import com.example.hhpuls.concertReservation.application.repository.UserTokenRepository;
import com.example.hhpuls.concertReservation.application.service.TokenService;
import com.example.hhpuls.concertReservation.common.enums.TokenStatus;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTokenServiceTest {

    @InjectMocks
    TokenService tokenService;

    @Mock
    UserTokenRepository userTokenRepository;

    @Test
    @DisplayName("토큰생성")
    public void 토큰생성() {
        // given
        String tokenValue = UUID.randomUUID().toString();
        UserToken mockUserToken = new UserToken(1L, 1L, tokenValue, TokenStatus.WAITING.getValue(), LocalDateTime.now(), null);
        when(userTokenRepository.save(any(UserToken.class))).thenReturn(mockUserToken);

        // when
        UserToken resultUserToken = tokenService.create(1L);

        // then
        Assertions.assertThat(resultUserToken.getToken()).isEqualTo(tokenValue);
        Assertions.assertThat(resultUserToken.getStatus()).isEqualTo(TokenStatus.WAITING.getValue());
        Assertions.assertThat(resultUserToken.getUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("토큰조회")
    public void 토큰조회() {
        // given
        String tokenValue = UUID.randomUUID().toString();
        Long userId = 1L;
        UserToken mockUserToken = new UserToken(1L, userId, tokenValue, TokenStatus.ACTIVE.getValue(), LocalDateTime.now(), null);
        given(userTokenRepository.findByUserIdAndTokenValue(userId, tokenValue)).willReturn(Optional.of(mockUserToken));

        // when
        UserToken resultUserToken = tokenService.find(userId, tokenValue);

        // then
        Assertions.assertThat(resultUserToken.getToken()).isEqualTo(tokenValue);
        Assertions.assertThat(resultUserToken.getStatus()).isEqualTo(TokenStatus.ACTIVE.getValue());
        Assertions.assertThat(resultUserToken.getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("토큰 만료")
    public void 토큰만료() {
        // given
        Long userId = 1L;
        String tokenValue = UUID.randomUUID().toString();
        UserToken mockUserToken = new UserToken(1L, userId, tokenValue, TokenStatus.ACTIVE.getValue(), LocalDateTime.now(), null);
        given(userTokenRepository.findByUserIdAndTokenValue(userId, tokenValue)).willReturn(Optional.of(mockUserToken));

        // when
        when(userTokenRepository.save(any(UserToken.class))).thenReturn(mockUserToken);
        UserToken resultUserToken = tokenService.expire(userId, tokenValue);

        // then
        Assertions.assertThat(resultUserToken.getStatus()).isEqualTo(TokenStatus.EXPIRE.getValue());
    }

    @Test
    @DisplayName("토큰 활성화")
    public void 토큰활성화() {
        // given
        Long userId = 1L;
        String tokenValue = UUID.randomUUID().toString();
        UserToken mockUserToken = new UserToken(1L, userId, tokenValue, TokenStatus.WAITING.getValue(), LocalDateTime.now(), null);
        given(userTokenRepository.findByUserIdAndTokenValue(userId, tokenValue)).willReturn(Optional.of(mockUserToken));

        // when
        when(userTokenRepository.save(any(UserToken.class))).thenReturn(mockUserToken);
        UserToken resultUserToken = tokenService.active(userId, tokenValue);

        // then
        Assertions.assertThat(resultUserToken.getStatus()).isEqualTo(TokenStatus.ACTIVE.getValue());
    }

}
*/
