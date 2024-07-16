package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertService concertService;

    public ConcertCommand.GetConcertInfoListResultCommand getConcertInfoList() {
        return this.concertService.getConcertInfoList();
    }

    public ConcertCommand.GetConcertDetailResultCommand getConcertDetailInfo(Long concertDetailId) {
        return this.concertService.getConcertDetail(concertDetailId);
    }

    public ConcertCommand.GetAvailableReserveConcertListResultCommand getAvailableReserveConcertList(Long concertId) {
        return this.concertService.getAvailableReserveConcertList(concertId);
    }

    public ConcertCommand.GetSeatInfoResultCommand getSeatInfo(Long concertDetailId) {
        return this.concertService.getSeatInfo(concertDetailId);
    }


}
