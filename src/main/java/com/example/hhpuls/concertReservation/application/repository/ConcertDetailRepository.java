package com.example.hhpuls.concertReservation.application.repository;

import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConcertDetailRepository {

    public List<ConcertDetail> findConcertDetails(LocalDateTime now);

    public Optional<ConcertDetail> findById(Long id);

    public List<ConcertDetail> findAvailableReserveConcertDetails(Long concertId, LocalDateTime now);

    public void saveAll(List<ConcertDetail> concertDetailList);
}
