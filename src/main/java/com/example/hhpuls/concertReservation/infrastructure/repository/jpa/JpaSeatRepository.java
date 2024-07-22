package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.concert.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaSeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT count(s) FROM Seat s WHERE s.concertDetailId = :concertDetailId AND s.status = :seatStatus")
    public Integer countBySeatStatus(Long concertDetailId, Integer seatStatus);

    public Integer countByConcertDetailId(Long concertDetailId);

    List<Seat> findByConcertDetailIdAndStatus(Long concertDetailId, Integer status);

    // TODO
    // update 시간 5분 되었는지 조건에 추가
    @Modifying
    @Query("UPDATE Seat SET status = 0 WHERE status = 1 AND updateDate < :nowMinusFiveMinutes")
    public void resetTemporarySeats(LocalDateTime nowMinusFiveMinutes);
}
