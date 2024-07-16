package com.example.hhpuls.concertReservation.application.facade;

import com.example.hhpuls.concertReservation.application.command.WaitingCommand;
import com.example.hhpuls.concertReservation.application.service.TokenService;
import com.example.hhpuls.concertReservation.common.exception.TokenException;
import com.example.hhpuls.concertReservation.domain.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;

    @Transactional
    public WaitingCommand.GetWaitingInfoResultCommand getWaitingInfo(WaitingCommand.GetWaitingInfoCommand command) {
        UserToken userToken = null;

        if(null == command.token() || "" == command.token()) {
            userToken = this.tokenService.createToken(command.userId());
        } else {
            userToken = this.tokenService.findToken(command.userId(), command.token());
        }

        if(null == userToken)
            throw new TokenException("유저토큰 정보 조회에 실패했습니다.");

        return WaitingCommand.GetWaitingInfoResultCommand.builder()
                .userId(userToken.getUserId())
                .token(userToken.getToken())
                .tokenStatus(userToken.getStatus())
                .createDate(userToken.getCreateDate())
                .activeDate(userToken.getActiveDate())
                .build();
    }
}
