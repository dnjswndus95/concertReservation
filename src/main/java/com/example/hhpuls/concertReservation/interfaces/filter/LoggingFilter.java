package com.example.hhpuls.concertReservation.interfaces.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/**")
public class LoggingFilter implements Filter {
    
    // 필터가 생성시 init하는 메서드
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    // request, response가 거칠 때 사용되는 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // request response 래핑
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(httpServletRequest, httpServletResponse);

        String uri = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsString());

        log.info("요청 uri : {}, requestBody : {}", uri, reqContent);

        Integer httpStatus = httpServletResponse.getStatus();
        String resContent = new String(httpServletResponse.getContentAsByteArray());

        // response를 console에 보여주면 내용이 사라지므로
        // 클라이언트에서 확인할 수 있도록 한번 복사
        httpServletResponse.copyBodyToResponse();

        log.info("status : {}, responseBody: {}", httpStatus, resContent);
    }

    // 필터가 소멸 시 사용되는 메서드
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
