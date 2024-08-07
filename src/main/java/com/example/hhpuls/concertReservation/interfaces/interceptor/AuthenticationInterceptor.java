package com.example.hhpuls.concertReservation.interfaces.interceptor;

import com.example.hhpuls.concertReservation.application.service.WaitingQueueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final WaitingQueueService waitingQueueService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("Authorization");

        // 토큰 검증 로직
        this.waitingQueueService.valid(Long.parseLong(userId));

        return true;
    }
}
