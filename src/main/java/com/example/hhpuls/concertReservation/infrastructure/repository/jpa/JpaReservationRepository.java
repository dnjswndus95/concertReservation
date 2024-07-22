package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserId(Long userId);

    @Modifying
    @Query("UPDATE Reservation SET status = 2 WHERE status = 0 AND createDate < :nowMinusFiveMinutes")
    void resetTemporaryReservation(LocalDateTime nowMinusFiveMinutes);
}
