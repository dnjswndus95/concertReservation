package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.service.TokenService;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;
    @Transactional
    public UserToken createToken(Long userId) {
        return this.tokenService.create(userId);
    }

    public UserToken findToken(Long userId) {
        UserToken userToken = this.tokenService.find(userId);

        return userToken;
    }
}
