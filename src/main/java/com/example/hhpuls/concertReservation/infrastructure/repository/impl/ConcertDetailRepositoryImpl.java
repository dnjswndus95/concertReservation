package com.example.hhpuls.concertReservation.infrastructure.repository.impl;

import com.example.hhpuls.concertReservation.application.repository.ConcertDetailRepository;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import com.example.hhpuls.concertReservation.infrastructure.repository.jpa.JpaConcertDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ConcertDetailRepositoryImpl implements ConcertDetailRepository {

    private final JpaConcertDetailRepository jpaConcertDetailRepository;

    @Override
    @Cacheable(value = "concertDetailList", key = "T(com.example.hhpuls.concertReservation.common.utils.DateTimeUtil).truncateToHour(#now)")
    public List<ConcertDetail> findConcertDetails(LocalDateTime now) {
        return this.jpaConcertDetailRepository.findConcertDetails(now);
    }

    @Override
    public Optional<ConcertDetail> findById(Long id) {
        return this.jpaConcertDetailRepository.findById(id);
    }

    @Override
    public List<ConcertDetail> findAvailableReserveConcertDetails(Long concertId, LocalDateTime now) {
        return this.jpaConcertDetailRepository.findAvailableReserveConcertDetails(concertId, now);
    }

    @Override
    public void saveAll(List<ConcertDetail> concertDetailList) {
        this.jpaConcertDetailRepository.saveAll(concertDetailList);
    }

}
