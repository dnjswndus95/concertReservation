package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithReservationDateModel;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class GetAvailableReserveConcertListDto {

    @Builder
    public record Response(
            List<ConcertInfoWithReservationDateDto> concertInfoList
    ) {

        public static GetAvailableReserveConcertListDto.Response fromCommand(ConcertCommand.GetAvailableReserveConcertListResultCommand command) {
            List<ConcertInfoWithReservationDateDto> cocertInfoList = new ArrayList<>();

            for (ConcertInfoWithReservationDateModel model : command.concertInfoList()) {
                cocertInfoList.add(
                        ConcertInfoWithReservationDateDto.from(model)
                );
            }

            return GetAvailableReserveConcertListDto.Response.builder()
                    .concertInfoList(cocertInfoList)
                    .build();
        }
    }
}

