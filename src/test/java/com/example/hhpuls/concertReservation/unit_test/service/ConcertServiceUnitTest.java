/*
package com.example.hhpuls.concertReservation.unit_test.service;

import com.example.hhpuls.concertReservation.application.command.ConcertCommand;
import com.example.hhpuls.concertReservation.application.model.ConcertInfoWithCreateDateModel;
import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
import com.example.hhpuls.concertReservation.application.repository.ConcertDetailRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.application.service.ConcertService;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.concert.Concert;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceUnitTest {

    @InjectMocks
    ConcertService concertService;

    @Mock
    ConcertDetailRepository concertDetailRepository;

    @Mock
    SeatRepository seatRepository;

    @Test
    @DisplayName("개별 콘서트 정보 조회")
    public void 개별콘서트정보조회() {
        // given
        Long concertDetailId = 2L;
        LocalDateTime concertDate = LocalDateTime.of(2024, 07, 11, 14, 0, 0);
        LocalDateTime availableReservationDate = LocalDateTime.of(2024, 07, 9, 14, 0, 0);

        given(seatRepository.countAll(concertDetailId)).willReturn(50);
        given(seatRepository.countBySeatStatus(concertDetailId, SeatStatus.PENDING.getValue())).willReturn(20);

        ConcertCommand.GetConcertDetailResultCommand mockResult
                = ConcertCommand.GetConcertDetailResultCommand.builder()
                .concertId(concertDetailId)
                .concertId(1L)
                .concertName("콘서트 이름2")
                .reservationDate(availableReservationDate)
                .concertDate(concertDate)
                .totalSeatCount(50)
                .availableReserveSeatCount(20)
                .build();
        Concert concert = new Concert(1L, "콘서트 이름2", null);
        ConcertDetail concertDetail = new ConcertDetail(null, 1L, concertDate, availableReservationDate, concert);

        given(concertDetailRepository.findById(concertDetailId)).willReturn(Optional.of(concertDetail));

        // when
        ConcertCommand.GetConcertDetailResultCommand result = this.concertService.getConcertDetail(concertDetailId);

        // then
        Assertions.assertThat(result).isEqualTo(mockResult);
        Assertions.assertThat(result.availableReserveSeatCount()).isEqualTo(mockResult.availableReserveSeatCount());
    }

    @Test
    @DisplayName("콘서트 단일 DB조회 찾지 못한 경우")
    public void 콘서트단일DB조회() {
        // given
        Long concertDetailId = -1L;

        // when
        CustomException e = assertThrows(CustomException.class, () -> concertService.findConcertDetail(concertDetailId));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("콘서트 상세정보가 없습니다.");
    }

    @Test
    @DisplayName("좌석 조회 실패")
    public void 좌석찾기_실패() {
        // given
        Long seatId = 1L;

        // when
        CustomException e = assertThrows(CustomException.class, () -> concertService.findSeat(seatId));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("좌석 정보를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("예약 가능한 콘서트 조회")
    public void 예약가능한코서트조회() {
        // given
        LocalDateTime concertDate = LocalDateTime.now().plusDays(2);
        LocalDateTime reservationDate = LocalDateTime.now().minusDays(2);
        LocalDateTime now = LocalDateTime.now();

        Concert concert = new Concert(1L, "싸이 콘서트", null);

        List<ConcertDetail> concertDetails = new ArrayList<>();
        ConcertDetail concertDetail1 = new ConcertDetail(1L, 1L, LocalDateTime.of(2024, 07, 12, 10, 0, 0), LocalDateTime.of(2024, 07, 8, 10, 0, 0), null);
        ConcertDetail concertDetail2 = new ConcertDetail(1L, 2L, LocalDateTime.of(2024, 07, 12, 14, 0, 0), LocalDateTime.of(2024, 07, 8, 10, 0, 0), null);

        concertDetails.add(concertDetail1);
        concertDetails.add(concertDetail2);

        given(concertDetailRepository.findConcertDetails(now)).willReturn(concertDetails);

        // when
        List<ConcertDetail> result = concertService.getConcertInfoList();

        // then
        Assertions.assertThat(result.get(0)).isEqualTo(concertDetail1);
        Assertions.assertThat(result.get(1)).isEqualTo(concertDetail2);
    }

    @Test
    @DisplayName("콘서트 좌석 조회")
    public void 콘서트_좌석조회_실패() {
        // given
        Long concertDetailId = 1L;

        ConcertDetail concertDetail = new ConcertDetail(concertDetailId, 1L, LocalDateTime.now(), LocalDateTime.now(), null);
        List<SeatInfoModel> seatList = new ArrayList<>();
        seatList.add(SeatInfoModel.builder()
                .seatId(1L)
                .seatPrice(1000)
                .seatNumber(1)
                .build());
        seatList.add(SeatInfoModel.builder()
                .seatId(2L)
                .seatPrice(2000)
                .seatNumber(2)
                .build());

        ConcertCommand.GetSeatInfoResultCommand mockResult = ConcertCommand.GetSeatInfoResultCommand
                .builder()
                .concertId(1L)
                .concertName("싸이 콘서트")
                .concertDetailId(concertDetailId)
                .concertDate(LocalDateTime.now())
                .seatList(seatList)
                .build();

        given(concertDetailRepository.findById(concertDetailId)).willReturn(Optional.of(concertDetail));
        given(seatRepository.findByConcertDetailId(1L, SeatStatus.PENDING.getValue())).willReturn(List.of());

        // when
        CustomException e = assertThrows(CustomException.class, () -> concertService.getSeatInfo(concertDetailId));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("콘서트의 좌석정보가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("콘서트 좌석 조회")
    public void 콘서트_좌석조회_성공() {
        // given
        Long concertDetailId = 1L;
        Concert concert = new Concert(1L, "싸이콘서트", null);

        ConcertDetail concertDetail = new ConcertDetail(concertDetailId, 1L, LocalDateTime.now(), LocalDateTime.now(), concert);

        List<SeatInfoModel> seatModelList = new ArrayList<>();
        seatModelList.add(SeatInfoModel.builder()
                .seatId(1L)
                .seatPrice(1000)
                .seatNumber(1)
                .build());
        seatModelList.add(SeatInfoModel.builder()
                .seatId(2L)
                .seatPrice(2000)
                .seatNumber(2)
                .build());

        ConcertCommand.GetSeatInfoResultCommand mockResult = ConcertCommand.GetSeatInfoResultCommand
                .builder()
                .concertId(1L)
                .concertName("싸이 콘서트")
                .concertDetailId(1L)
                .concertDate(LocalDateTime.now())
                .seatList(seatModelList)
                .build();

        List<Seat> seatList = new ArrayList<>();
        Seat seat1 = new Seat(1L, concertDetailId, 1, 1000, 0);
        Seat seat2 = new Seat(2L, concertDetailId, 2, 2000, 0);
        seatList.add(seat1);
        seatList.add(seat2);

        given(concertDetailRepository.findById(concertDetailId)).willReturn(Optional.of(concertDetail));
        given(seatRepository.findByConcertDetailId(1L, SeatStatus.PENDING.getValue())).willReturn(seatList);

        // when
        ConcertCommand.GetSeatInfoResultCommand result = concertService.getSeatInfo(concertDetailId);

        // then
        Assertions.assertThat(result.seatList().size()).isEqualTo(2);
        Assertions.assertThat(result.seatList().get(0)).isEqualTo(mockResult.seatList().get(0));
        Assertions.assertThat(result.seatList().get(1)).isEqualTo(mockResult.seatList().get(1));
    }
}
*/
