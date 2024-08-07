package com.example.hhpuls.concertReservation.interfaces.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SpringConfig {

    @Value("${spring.session.redis.namespace}")
    private String sessionRedisNameSpace;

    @Value("${spring.profiles.active}")
    private String activeProfile;
}
