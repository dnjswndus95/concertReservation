package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaSeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT count(s) FROM Seat s WHERE s.concertDetailId = :concertDetailId AND s.status = :seatStatus")
    public Integer countBySeatStatus(Long concertDetailId, Integer seatStatus);

    public Integer countByConcertDetailId(Long concertDetailId);

    List<Seat> findByConcertDetailIdAndStatus(Long concertDetailId, Integer status);
}
