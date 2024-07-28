package com.example.hhpuls.concertReservation.domain.domain.payment;

import com.example.hhpuls.concertReservation.common.exception.CustomException;
import com.example.hhpuls.concertReservation.domain.domain.BaseTime;
import com.example.hhpuls.concertReservation.domain.error_code.ErrorCode;
import jakarta.persistence.*;
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

    @Version
    private Integer version = 0;


    public void charge(Integer price) {
        if(price < 0)
            throw new CustomException(ErrorCode.POINT_CHARGE_AMOUNT_MUST_POSITIVE_NUMBER);

        this.point += price;
    }

    public void use(Integer price) {
        if(price < 0)
            throw new CustomException(ErrorCode.POINT_CHARGE_AMOUNT_MUST_POSITIVE_NUMBER);

        if(this.point - price < 0)
            throw new CustomException(ErrorCode.POINT_USE_AMOUNT_BIGGER_THAN_RESERVE);

        this.point -= price;
    }
}
