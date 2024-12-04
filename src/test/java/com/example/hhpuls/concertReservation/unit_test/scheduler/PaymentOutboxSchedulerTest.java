package com.example.hhpuls.concertReservation.unit_test.scheduler;

import com.example.hhpuls.concertReservation.application.repository.PaymentOutboxRepository;
import com.example.hhpuls.concertReservation.common.enums.PaymentOutboxStatus;
import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;
import com.example.hhpuls.concertReservation.interfaces.scheduler.PaymentOutboxScheduler;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PaymentOutboxSchedulerTest {

    @Autowired
    PaymentOutboxRepository paymentOutboxRepository;

    @Autowired
    PaymentOutboxScheduler paymentOutboxScheduler;

    @Test
    @DisplayName("INIT 상태인 Outbox 중 생성된 지 5분이상 된 데이터를 메시지 재전송하여 PUBLISHED 상태로 업데이트 한다.")
    void 스케줄러_테스트() {
        // given
        int initOutboxNum = 3;
        List<PaymentOutbox> paymentOutboxList = paymentOutboxRepository.findOutboxByStatusAndCreateDate(PaymentOutboxStatus.INIT.getValue(), LocalDateTime.now().plusMinutes(5));

        // when
        paymentOutboxScheduler.retryOutboxPublish();

        // then
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    Integer publishedOutboxNum = this.paymentOutboxRepository.findOutBoxByStatus(PaymentOutboxStatus.PUBLISHED.getValue()).size();
                    Assertions.assertThat(publishedOutboxNum).isEqualTo(initOutboxNum);
;                });
    }
}
