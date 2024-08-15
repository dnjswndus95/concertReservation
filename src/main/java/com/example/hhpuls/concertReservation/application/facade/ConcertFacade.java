package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithSeatList;
import com.example.hhpuls.concertReservation.application.service.ConcertService;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertService concertService;

    public List<ConcertDetail> getConcertInfoList() {
        return this.concertService.getConcertInfoList();
    }

    public ConcertCommand.GetConcertDetailResultCommand getConcertDetailInfo(Long concertDetailId) {
        return this.concertService.getConcertDetail(concertDetailId);
    }

    public List<ConcertDetail> getAvailableReserveConcertList(Long concertId) {
        return this.concertService.getAvailableReserveConcertList(concertId);
    }

    public ConcertInfoWithSeatList getSeatInfo(Long concertDetailId) {
        return this.concertService.getSeatInfo(concertDetailId);
    }


}
