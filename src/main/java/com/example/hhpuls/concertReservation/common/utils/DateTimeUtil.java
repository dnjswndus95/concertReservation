package com.example.hhpuls.concertReservation.common.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    // @Cacheable의 key값을 시간으로만 전달하기 위해 시간만 파싱
    public static String truncateToHour(LocalDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.HOURS).toString();
    }
}
