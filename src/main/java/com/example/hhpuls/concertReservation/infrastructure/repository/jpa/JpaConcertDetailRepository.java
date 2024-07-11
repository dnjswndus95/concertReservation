package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaConcertDetailRepository extends JpaRepository<ConcertDetail, Long> {

    @Query("SELECT cd FROM ConcertDetail cd WHERE cd.concertDate > :now")
    List<ConcertDetail> findConcertDetails(LocalDateTime now);

    @Query("SELECT cd FROM ConcertDetail cd WHERE cd.concertId = :concertId AND cd.concertDate > :now AND cd.availableReservationDate < :now")
    List<ConcertDetail> findAvailableReserveConcertDetails(Long concertId, LocalDateTime now);
}
