package com.example.hhpuls.concertReservation.presentation.dto.waiting;

import lombok.Builder;

public class GetWaitingQueueInfoDto {
    /*@Builder
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
    }*/

    @Builder
    public record Response(
            Long rank
    ) {
        public static GetWaitingQueueInfoDto.Response from(Long rank) {
            return Response.builder()
                    .rank(rank)
                    .build();
        }
    }
}
