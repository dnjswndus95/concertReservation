package com.example.hhpuls.concertReservation.interfaces.config;

import com.example.hhpuls.concertReservation.interfaces.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Interceptor를 등록하기 위해 WebMvcConfigurer 사용
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;

    public WebConfig(AuthenticationInterceptor authenticationInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
    }

    // Interceptor 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 특정 url에서만 인터셉터를 타도록 등록
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/payment/**", "/reservation/**", "/concert/**");
    }
}
