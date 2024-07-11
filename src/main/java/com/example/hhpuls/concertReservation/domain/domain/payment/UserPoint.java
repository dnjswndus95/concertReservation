package com.example.hhpuls.concertReservation.domain.domain.payment;

import com.example.hhpuls.concertReservation.common.exception.UserPointException;
import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "user_point")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Proxy 조회를 위해 private x
@AllArgsConstructor
public class UserPoint extends BaseTime {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "point")
    private Integer point;


    public void chargePoint(Integer price) {
        if(price < 0)
            throw new UserPointException("포인트 충전은 양수만 가능합니다.");

        this.point += price;
    }

    public void usePoint(Integer price) {
        if(price < 0)
            throw new UserPointException("포인트 충전은 양수만 가능합니다.");

        if(this.point - price < 0)
            throw new UserPointException("가진 금액보다 많은 금액은 사용할 수 없습니다.");

        this.point -= price;
    }
}
