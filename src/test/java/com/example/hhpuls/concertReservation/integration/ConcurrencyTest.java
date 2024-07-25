package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.facade.PointFacade;
import com.example.hhpuls.concertReservation.application.facade.ReservationFacade;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.common.enums.SeatStatus;
import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@ActiveProfiles("testdb")
@Sql("/sql/payment-test-data-init.sql")
public class ConcurrencyTest {

    @Autowired
    PointFacade pointFacade;

    final int numberOfThreads = 200;
    @DisplayName("유저 포인트 충전 낙관적락 테스트")
    @Test
    void 한_유저의_포인트_충전이_동시에_들어올_경우_한_요청만_성공한다() throws InterruptedException {
        // given
        Long userId = 1L;
        PointCommand.ChargePointCommand chargeCommand = PointCommand.ChargePointCommand.builder()
                .userId(userId)
                .point(1)
                .build();

        AtomicInteger failCount = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // when
        for(int i = 0; i < numberOfThreads; ++i) {
            executorService.execute(() -> {
                try {
                    pointFacade.chargePoint(chargeCommand);
                } catch (Exception e) {
                    failCount.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        UserPoint userPoint = pointFacade.getUserPoint(userId);

        System.out.println("실제 요청 건 수 : " + numberOfThreads);
        System.out.println("실패한 요청 건 수 : " + failCount.get());

        // then
        Assertions.assertThat(failCount.get()).isEqualTo(numberOfThreads - 1);
        Assertions.assertThat(userPoint.getPoint()).isEqualTo(1);
        Assertions.assertThat(userPoint.getVersion()).isEqualTo(1);
    }
}
