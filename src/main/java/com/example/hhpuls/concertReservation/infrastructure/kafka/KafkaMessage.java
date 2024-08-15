package com.example.hhpuls.concertReservation.infrastructure.kafka;

import com.example.hhpuls.concertReservation.domain.domain.payment.event.PaymentEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaMessage<T> {

    private Long id;
    private T message;

    public KafkaMessage(Long id, T message) {
        this.id = id;
        this.message = message;
    }

    public KafkaMessage(PaymentEvent paymentEvent) {
    }

    public static <T> KafkaMessage<T> of(Long id, T message) {
        return new KafkaMessage<>(id, message);
    }
}
