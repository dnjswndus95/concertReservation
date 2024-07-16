package com.example.hhpuls.concertReservation.presentation.dto.payment;

import com.example.hhpuls.concertReservation.application.model.PaymentInfoModel;
import com.example.hhpuls.concertReservation.application.model.SeatInfoModel;
import com.example.hhpuls.concertReservation.presentation.dto.concert.SeatInfoDto;
import lombok.Builder;


@Builder
public record PaymentInfoDto(
        Long paymentId,
        Integer paymentStatus,
        Integer paymentPrice
) {
    public static PaymentInfoDto from(PaymentInfoModel model) {
        return PaymentInfoDto.builder()
                .paymentId(model.paymentId())
                .paymentStatus(model.paymentStatus())
                .paymentPrice(model.paymentPrice())
                .build();
    }
}
