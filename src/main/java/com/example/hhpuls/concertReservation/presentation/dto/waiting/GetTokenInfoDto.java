package com.example.hhpuls.concertReservation.presentation.dto.waiting;

import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import lombok.Builder;

import java.time.LocalDateTime;

public class GetTokenInfoDto {
    @Builder
    public record Response(
            Long userId,
            String token,
            Integer tokenStatus,
            LocalDateTime activeDate,
            LocalDateTime createDate
    ) {
        public static GetTokenInfoDto.Response from(UserToken userToken) {
            return Response.builder()
                    .userId(userToken.getUserId())
                    .token(userToken.getToken())
                    .tokenStatus(userToken.getStatus())
                    .activeDate(userToken.getActiveDate())
                    .createDate(userToken.getCreateDate())
                    .build();
        }
    }
}
