package com.example.hhpuls.concertReservation.infrastructure.repository.bulk;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

public class ConcertDetailBulkInsert {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/concert_reservation";
        String user = "root";
        String password = "juyeoun1158!";

        String insertQuery = "INSERT INTO status_concert_detail (concert_id, concert_date, available_reservation_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            conn.setAutoCommit(false);
            Random random = new Random();
            LocalDateTime now = LocalDateTime.of(2024, 2, 1, 19, 0, 0);

            for (int i = 0; i < 2000000; i++) {
                Integer status = random.nextInt(3);
                LocalDateTime concertDate = now.plusDays(random.nextInt(365) + 1).plusHours(random.nextInt(24) + 1).plusMinutes(random.nextInt(60) + 1);
                LocalDateTime availableReservationDate = now.minusDays(random.nextInt(365) + 1).plusHours(random.nextInt(24) + 1).plusMinutes(random.nextInt(60) + 1);

                pstmt.setInt(1, i % 50000 + 1);
                pstmt.setTimestamp(2, Timestamp.valueOf(concertDate));
                pstmt.setTimestamp(3, Timestamp.valueOf(availableReservationDate));
                pstmt.setInt(4, status);
                pstmt.addBatch();

                ResultSetMetaData metaData = pstmt.getMetaData();

                if (i % 1000 == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
