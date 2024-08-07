package com.example.hhpuls.concertReservation.integration;

import com.example.hhpuls.concertReservation.application.repository.ConcertDetailRepository;
import com.example.hhpuls.concertReservation.application.repository.SeatRepository;
import com.example.hhpuls.concertReservation.application.service.ConcertService;
import com.example.hhpuls.concertReservation.domain.domain.concert.ConcertDetail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("testdb")
public class ConcertCachingIntegrationTest {

    @Autowired
    ConcertService concertService;

    @Autowired
    ConcertDetailRepository concertDetailRepository;

    @Autowired
    SeatRepository seatRepository;

    @Test
    @DisplayName("getConcertDetailsWithDB")
    public void 디비에서_콘서트_목록을_조회한다() {
        // when
        List<ConcertDetail> concertInfoList = concertService.getConcertInfoList();

        // then
        Assertions.assertThat(concertInfoList.size()).isEqualTo(300);
    }

    @Test
    @DisplayName("getConcertDetailsWithCacheMemory")
    public void 캐시에_저장된_콘서트_목록을_조회한다() {
        // when
        List<ConcertDetail> concertInfoList = concertService.getConcertInfoList();

        System.out.println("zzzz");
        // then
        Assertions.assertThat(concertInfoList.size()).isEqualTo(300);
    }

    @Test
    public void DB_조회와_캐시_조회를_비교한다() {
        // 1. DB에서 조회
        long startTimeDb = System.currentTimeMillis();
        List<ConcertDetail> dbResults = concertService.getConcertInfoList();
        long endTimeDb = System.currentTimeMillis();
        long durationDb = endTimeDb - startTimeDb;
        System.out.println("DB 조회 시간: " + durationDb + " ms");

        // 2. Cache에서 조회
        long startTimeCache = System.currentTimeMillis();
        List<ConcertDetail> cacheResults = concertService.getConcertInfoList();
        long endTimeCache = System.currentTimeMillis();
        long durationCache = endTimeCache - startTimeCache;
        System.out.println("Cache 조회 시간: " + durationCache + " ms");

        // 두 리스트가 동일한지 확인
        assertEquals(dbResults, cacheResults);
    }
}
