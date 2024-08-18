package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.repository.PaymentOutboxRepository;
import com.example.hhpuls.concertReservation.application.repository.UserPointRepository;
import com.example.hhpuls.concertReservation.common.enums.PaymentOutboxStatus;
import com.example.hhpuls.concertReservation.domain.domain.payment.PaymentOutbox;
import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import com.example.hhpuls.concertReservation.domain.domain.payment.consumer.PaymentConsumer;
import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import com.example.hhpuls.concertReservation.infrastructure.kafka.KafkaMessage;
import com.example.hhpuls.concertReservation.infrastructure.kafka.payment.PaymentKafkaMessageSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class PaymentKafkaIntegrationTest {

    @Autowired
    PaymentKafkaMessageSender paymentKafkaMessageSender;

    @Autowired
    PaymentConsumer paymentConsumer;

    @Autowired
    UserPointRepository userPointRepository;

    @Autowired
    PaymentOutboxRepository paymentOutboxRepository;


    @Test
    void 카프카_메세지를_발행하면_컨슘하여_유저의_포인트를_차감한다() throws InterruptedException {
        Long userId = 1L;
        Long paymentId = 1L;
        Integer price = 1000;
        String outBoxId = UUID.randomUUID().toString();

        UserPoint userPoint = userPointRepository.find(userId).orElseThrow(
                () -> new IllegalArgumentException("유저 조회 실패"));


        PaymentEvent paymentEvent = new PaymentEvent(userId, paymentId, price, outBoxId);
        KafkaMessage<PaymentEvent> message = new KafkaMessage<>(paymentId, paymentEvent);

        paymentKafkaMessageSender.send(message);

        Thread.sleep(5000);

        UserPoint updatedUserPoint = userPointRepository.find(userId).orElseThrow(
                () -> new IllegalArgumentException("유저 조회 실패"));

        // 유저의 금액이 차감되었는지 검증
        Assertions.assertThat(userPoint.getPoint() - price).isEqualTo(updatedUserPoint.getPoint());
    }

    @Test
    void 카프카_메세지를_발행하면_컨슘하여_Outbox_상태를_PUBLISHED로_업데이트한다() throws InterruptedException {
        Long userId = 1L;
        Long paymentId = 1L;
        Integer price = 1000;
        String outBoxId = UUID.randomUUID().toString();

        PaymentEvent paymentEvent = new PaymentEvent(userId, paymentId, price, outBoxId);
        KafkaMessage<PaymentEvent> message = new KafkaMessage<>(paymentId, paymentEvent);

        paymentKafkaMessageSender.send(message);
        Thread.sleep(5000);

        PaymentOutbox paymentOutbox = paymentOutboxRepository.findById(outBoxId).orElseThrow(
                () -> new IllegalArgumentException("OutBox 조회 실패")
        );

        Assertions.assertThat(paymentOutbox.getOutboxStatus()).isEqualTo(PaymentOutboxStatus.PUBLISHED.getValue());
    }
}
