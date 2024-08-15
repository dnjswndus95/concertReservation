package com.example.hhpuls.concertReservation.infrastructure.repository.jpa;

import com.example.hhpuls.concertReservation.domain.domain.payment.UserPoint;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserPointRepository extends JpaRepository<UserPoint, Long> {

    public Optional<UserPoint> findByUserId(Long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = "SELECT up FROM UserPoint up WHERE up.userId = :userId")
    public Optional<UserPoint> findByIdWithOptimisticLock(@Param("userId") Long userId);
}
