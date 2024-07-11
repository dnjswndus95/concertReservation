package com.example.hhpuls.concertReservation.domain.domain;

import com.example.hhpuls.concertReservation.common.enums.TokenStatus;
import com.example.hhpuls.concertReservation.common.exception.TokenException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "token", length = 36)
    private String token;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active_date")
    private LocalDateTime activeDate;

    @PrePersist
    protected void onCreate() {
        this.status = TokenStatus.WAITING.getValue();
        this.createDate = LocalDateTime.now();
    }


    public void activeToken() {
        if(TokenStatus.EXPIRE.getValue() == this.status)
            throw new TokenException("이미 만료된 토큰입니다.");
        if(TokenStatus.ACTIVE.getValue() == this.status)
            throw new TokenException("이미 활성화된 토큰입니다.");

        this.status = TokenStatus.ACTIVE.getValue();
        this.activeDate = LocalDateTime.now();
    }

    public void expireToken() {
        this.status = TokenStatus.EXPIRE.getValue();
    }
}