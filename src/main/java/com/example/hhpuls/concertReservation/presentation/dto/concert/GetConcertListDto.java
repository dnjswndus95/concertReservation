package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class GetConcertListDto {

    @Builder
    public record Response(
            List<ConcertInfoWithCreateDateDto> concertList
    ) {
        public static GetConcertListDto.Response from(List<ConcertDetail> concertDetailList) {
            List<ConcertInfoWithCreateDateDto> list = new ArrayList<>();

            for (ConcertDetail concertDetail : concertDetailList) {
                list.add(ConcertInfoWithCreateDateDto.from(concertDetail));
            }

            return Response.builder()
                    .concertList(list)
                    .build();
        }
    }
}
