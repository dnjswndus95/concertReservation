package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithCreateDateModel;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithReservationDateModel;
import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
import com.example.hhpuls.concertReservation.application.repository.ConcertDetailRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.ConcertException;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ConcertServiceImpl implements ConcertService {

    private final ConcertDetailRepository concertDetailRepository;
    private final SeatRepository seatRepository;

    @Override
    public ConcertCommand.GetConcertInfoListResultCommand getConcertInfoList() {
        LocalDateTime now = LocalDateTime.now();
        List<ConcertDetail> concertDetails = this.concertDetailRepository.findConcertDetails(now);

        List<ConcertInfoWithCreateDateModel> concertInfoWithCreateDateModelList = new ArrayList<>();

        for (ConcertDetail concertDetail : concertDetails) {
            concertInfoWithCreateDateModelList.add(ConcertInfoWithCreateDateModel.fromDomain(concertDetail));
        }

        return ConcertCommand.GetConcertInfoListResultCommand.builder()
                .concertInfoList(concertInfoWithCreateDateModelList)
                .build();
    }

    @Override
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

    @Override
    public ConcertCommand.GetAvailableReserveConcertListResultCommand getAvailableReserveConcertList(Long concertId) {
        List<ConcertDetail> findAvailableReserveConcertDetails = this.concertDetailRepository.findAvailableReserveConcertDetails(concertId, LocalDateTime.now());

        List<ConcertInfoWithReservationDateModel> concertInfoList = new ArrayList<>();

        for (ConcertDetail concertDetail : findAvailableReserveConcertDetails) {
            concertInfoList.add(
                    ConcertInfoWithReservationDateModel.builder()
                            .concertId(concertDetail.getConcertId())
                            .concertName(concertDetail.getConcert().getName())
                            .concertDetailId(concertDetail.getId())
                            .concertDate(concertDetail.getConcertDate())
                            .reservationDate(concertDetail.getAvailableReservationDate())
                            .build()
            );
        }

        return ConcertCommand.GetAvailableReserveConcertListResultCommand.builder()
                .concertInfoList(concertInfoList)
                .build();
    }

    @Override
    public ConcertCommand.GetSeatInfoResultCommand getSeatInfo(Long concertDetailId) {
        ConcertDetail findConcertDetail = this.findConcertDetail(concertDetailId);

        List<Seat> findSeatInfoList = this.seatRepository.findByConcertDetailId(concertDetailId, SeatStatus.PENDING.getValue());

        if(findSeatInfoList.isEmpty())
            throw new ConcertException("콘서트의 좌석정보가 존재하지 않습니다.");

        List<SeatInfoModel> seatInfoList = new ArrayList<>();
        for (Seat seat : findSeatInfoList) {
            seatInfoList.add(SeatInfoModel.fromDomain(seat));
        }

        return ConcertCommand.GetSeatInfoResultCommand.builder()
                .concertId(findConcertDetail.getConcertId())
                .concertName(findConcertDetail.getConcert().getName())
                .concertDetailId(findConcertDetail.getId())
                .concertDate(findConcertDetail.getConcertDate())
                .seatList(seatInfoList)
                .build();
    }

    @Override
    public ConcertDetail findConcertDetail(Long concertDetailId) {
        return this.concertDetailRepository.findById(concertDetailId).orElseThrow(
                () -> new ConcertException("콘서트 상세정보가 없습니다.")
        );
    }

    @Override
    public Seat findSeat(Long seatId) {
        return this.seatRepository.findById(seatId).orElseThrow(
                () -> new ConcertException("좌석 정보를 찾을 수 없습니다.")
        );
    }
}
