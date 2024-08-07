package com.example.hhpuls.concertReservation.infrastructure.repository.bulk;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

public class ConcertBulkInsert {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/concert_reservation";
        String user = "root";
        String password = "juyeoun1158!";

        String insertQuery = "INSERT INTO concert (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            conn.setAutoCommit(false);
            Random random = new Random();
            LocalDateTime now = LocalDateTime.of(2024, 8, 7, 19, 0, 0);

            for (int i = 0; i < 50000; i++) {
                pstmt.setString(1, "콘서트" + i);
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
