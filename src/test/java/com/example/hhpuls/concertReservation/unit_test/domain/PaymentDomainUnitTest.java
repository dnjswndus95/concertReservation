package com.example.hhpuls.concertReservation.unit_test.domain;

import com.example.hhpuls.concertReservation.common.enums.PaymentStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.payment.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentDomainUnitTest {

    @Test
    @DisplayName("결제완료 업데이트")
    public void 결제완료() {
        // given
        Payment payment = new Payment(1L, 1L, 1000, PaymentStatus.WAITING.getValue());

        // when
        payment.done();

        // then
        Assertions.assertThat(payment.getStatus()).isEqualTo(PaymentStatus.DONE.getValue());
    }

    @Test
    @DisplayName("결제취소 업데이트")
    public void 결제취소() {
        // given
        Payment payment = new Payment(1L, 1L, 1000, PaymentStatus.WAITING.getValue());

        // when
        payment.cancel();

        // then
        Assertions.assertThat(payment.getStatus()).isEqualTo(PaymentStatus.CANCEL.getValue());
    }

    @Test
    @DisplayName("결제완료 실패")
    public void 결제완료_실패() {
        // given
        Payment payment = new Payment(1L, 1L, 1000, PaymentStatus.CANCEL.getValue());

        // when
        CustomException e = assertThrows(CustomException.class, () -> payment.done());

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("결제완료할 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("결제취소 실패")
    public void 결제취소_실패() {
        // given
        Payment payment = new Payment(1L, 1L, 1000, PaymentStatus.DONE.getValue());

        // when
        CustomException e = assertThrows(CustomException.class, () -> payment.cancel());

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("결제취소할 수 없는 상태입니다.");
    }

}
