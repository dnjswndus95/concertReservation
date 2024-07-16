package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;

public interface ConcertService {

    public ConcertCommand.GetConcertInfoListResultCommand getConcertInfoList();

    public ConcertCommand.GetConcertDetailResultCommand getConcertDetail(Long concertDetailId);

    public ConcertCommand.GetAvailableReserveConcertListResultCommand getAvailableReserveConcertList(Long concertId);

    public ConcertCommand.GetSeatInfoResultCommand getSeatInfo(Long concertDetailId);

    public ConcertDetail findConcertDetail(Long concertDetailId);

    public Seat findSeat(Long seatId);
}
