package com.example.hhpuls.concertReservation.interfaces.config;

import com.example.hhpuls.concertReservation.interfaces.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // 내가 만든 필터를 순서를 지정하여 빈으로 등록해준다.
    @Bean
    public FilterRegistrationBean exampleFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LoggingFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
