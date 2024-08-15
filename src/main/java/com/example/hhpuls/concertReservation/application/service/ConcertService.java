package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithSeatList;
import com.example.hhpuls.concertReservation.application.repository.ConcertDetailRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ConcertService {

    private final ConcertDetailRepository concertDetailRepository;
    private final SeatRepository seatRepository;

    public List<ConcertDetail> getConcertInfoList() {
        LocalDateTime now = LocalDateTime.now();
        return this.concertDetailRepository.findConcertDetails(now);
    }

    public ConcertCommand.GetConcertDetailResultCommand getConcertDetail(Long concertDetailId) {
        Integer totalSeatCount = this.seatRepository.countAll(concertDetailId);
        Integer availableReserveSeatCount = this.seatRepository.countBySeatStatus(concertDetailId, SeatStatus.PENDING.getValue());

        ConcertDetail findConcertDetail = this.findConcertDetail(concertDetailId);

        return ConcertCommand.GetConcertDetailResultCommand.builder()
                .concertId(findConcertDetail.getConcertId())
                .concertName(findConcertDetail.getConcert().getName())
                .createDate(findConcertDetail.getConcert().getCreateDate())
                .concertDetailId(findConcertDetail.getId())
                .reservationDate(findConcertDetail.getAvailableReservationDate())
                .createDate(findConcertDetail.getCreateDate())
                .concertDate(findConcertDetail.getConcertDate())
                .totalSeatCount(totalSeatCount)
                .availableReserveSeatCount(availableReserveSeatCount)
                .build();

    }

    public List<ConcertDetail> getAvailableReserveConcertList(Long concertId) {
        return this.concertDetailRepository.findAvailableReserveConcertDetails(concertId, LocalDateTime.now());
    }

    public ConcertInfoWithSeatList getSeatInfo(Long concertDetailId) {
        ConcertDetail findConcertDetail = this.findConcertDetail(concertDetailId);

        List<Seat> findSeatInfoList = this.seatRepository.findByConcertDetailId(concertDetailId, SeatStatus.PENDING.getValue());

        if(findSeatInfoList.isEmpty())
            throw new CustomException(ErrorCode.CONCERT_SEAT_INFO_NOT_FOUND);

        return ConcertInfoWithSeatList.builder()
                .concertId(findConcertDetail.getConcertId())
                .concertName(findConcertDetail.getConcert().getName())
                .concertDetailId(findConcertDetail.getId())
                .concertDate(findConcertDetail.getConcertDate())
                .seatList(findSeatInfoList)
                .build();
    }

    public ConcertDetail findConcertDetail(Long concertDetailId) {
        return this.concertDetailRepository.findById(concertDetailId).orElseThrow(
                () -> new CustomException(ErrorCode.CONCERT_INFO_NOT_FOUND)
        );
    }
}
