package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class GetAvailableReserveConcertListDto {

    @Builder
    public record Response(
            List<ConcertInfoWithReservationDateDto> concertInfoList
    ) {

        public static GetAvailableReserveConcertListDto.Response from(List<ConcertDetail> concertDetailList) {
            List<ConcertInfoWithReservationDateDto> concertInfoList = new ArrayList<>();

            for (ConcertDetail concertDetail : concertDetailList) {
                concertInfoList.add(ConcertInfoWithReservationDateDto.from(concertDetail));
            }


            return GetAvailableReserveConcertListDto.Response.builder()
                    .concertInfoList(concertInfoList)
                    .build();
        }
    }
}

