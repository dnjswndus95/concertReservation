package com.example.hhpuls.concertReservation.presentation.dto.concert;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithCreateDateModel;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class GetConcertListDto {

    @Builder
    public record Response(
            List<ConcertInfoWithCreateDateDto> concertList
    ) {
        public static GetConcertListDto.Response fromCommand(ConcertCommand.GetConcertInfoListResultCommand command) {
            List<ConcertInfoWithCreateDateDto> list = new ArrayList<>();

            for (ConcertInfoWithCreateDateModel concertInfoWithCreateDateModel : command.concertInfoList()) {
                ConcertInfoWithCreateDateDto.from(concertInfoWithCreateDateModel);
                list.add(ConcertInfoWithCreateDateDto.from(concertInfoWithCreateDateModel));
            }

            return Response.builder()
                    .concertList(list)
                    .build();
        }
    }
}
