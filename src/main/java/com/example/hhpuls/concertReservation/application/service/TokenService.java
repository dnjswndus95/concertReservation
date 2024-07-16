package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    public UserToken expireToken(Long userId, String token);
    public UserToken activeToken(Long userId, String token);

    public UserToken findToken(Long userId, String token);
    public UserToken createToken(Long userId);

}
