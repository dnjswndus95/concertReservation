package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.command.PointCommand;
import com.example.hhpuls.concertReservation.application.facade.PointFacade;
import com.example.hhpuls.concertReservation.application.service.PointService;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import java.util.concurrent.*;

@SpringBootTest
@ActiveProfiles("testdb")
@Sql("/sql/point-test-data-init.sql")
public class PointFacadeIntegrationTest {

    @Autowired
    PointFacade pointFacade;

    @Autowired
    PointService pointService;


    @DisplayName("유저가 포인트를 조회한다.")
    @Test
    void 유저는_자신의_포인트_조회를_할_수_있다() {
        // given
        Long userId = 1L;

        // when
        UserPoint result = pointFacade.getUserPoint(userId);

        // then
        Assertions.assertThat(result.getUserId()).isEqualTo(1L);
        Assertions.assertThat(result.getPoint()).isEqualTo(2000);
    }

    @DisplayName("유저가 포인트를 충전한다.")
    @Test
    void 유저는_포인트를_충전할_수_있다() {
        // given
        PointCommand.ChargePointCommand chargeCommand = PointCommand.ChargePointCommand.builder()
                .userId(1L)
                .point(3000)
                .build();

        // when
        UserPoint result = pointFacade.chargePoint(chargeCommand);

        // then
        Assertions.assertThat(result.getPoint()).isEqualTo(5000);
    }

    @DisplayName("유저 포인트 충전 낙관적락 테스트")
    @Test
    void 유저_포인트_충전_낙관적_락_테스트() throws InterruptedException {
        // given
        Long userId = 1L;
        PointCommand.ChargePointCommand chargeCommand = PointCommand.ChargePointCommand.builder()
                .userId(userId)
                .point(3000)
                .build();
        final int numberOfThreads = 10;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        ConcurrentLinkedDeque<Integer> failedQueue = new ConcurrentLinkedDeque<>();

        // when
        for(int i = 0; i < numberOfThreads; ++i) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                     pointFacade.chargePoint(chargeCommand);
                } catch (Exception e) {
                    failedQueue.add(finalI);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        UserPoint userPoint = pointFacade.getUserPoint(userId);

        // then
        Assertions.assertThat(failedQueue.size()).isEqualTo(numberOfThreads - 1);
        Assertions.assertThat(userPoint.getPoint()).isEqualTo(5000);
        Assertions.assertThat(userPoint.getVersion()).isEqualTo(1);
    }
}
