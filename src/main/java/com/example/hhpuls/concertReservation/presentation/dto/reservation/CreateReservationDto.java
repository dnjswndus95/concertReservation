package com.example.hhpuls.concertReservation.presentation.dto.reservation;

import com.example.hhpuls.concertReservation.presentation.dto.concert.ConcertInfoWithCreateDateDto;
import com.example.hhpuls.concertReservation.presentation.dto.concert.SeatInfoDto;
import com.example.hhpuls.concertReservation.presentation.dto.payment.PaymentInfoDto;
import lombok.Builder;

public class CreateReservationDto {

    @Builder
    public record Request(
            Long userId,
            Long concertDetailId,
            Long seatId
    ) {}

    @Builder
    public record Response(
            ConcertInfoWithCreateDateDto concertInfo,
            SeatInfoDto seatInfo,
            PaymentInfoDto paymentInfo

    ) {}
}

/**
 * - Body:
 * {
 *     "concertInfo": { // 콘서트 정보
 *         "concertId": Long, // 콘서트 pk
 *         "concertName": String, // 콘서트 이름
 *         "concertDate": datetime, // 콘서트 일시
 *         "concertDetailId": String, // 콘서트 상세 pk
 *     },
 *     "seatInfo": { // 좌석정보
 *         "seatId": Long, // 좌석 pk
 *         "seatNumber": Integer // 좌석 번호
 *     },
 *     "paymentInfo": { // 결제정보
 *         "paymentId": Long, // 결제내역 pk
 *         "paymentStatus": Integer, // 결제내역 상태
 *         "paymentPrice": Integer // 결제금액
 *     }
 * }
 */
