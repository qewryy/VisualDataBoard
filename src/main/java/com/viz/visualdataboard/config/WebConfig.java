package com.viz.visualdataboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${CLIENT_DOMAIN}")
    private String clientDomain;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://"+clientDomain+":3000") // 리액트 앱의 도메인을 허용
                .allowedMethods("*") // 모든 HTTP 메서드를 허용
                .allowCredentials(true) // 쿠키를 포함한 요청을 허용
                .maxAge(3600); // 사전 전달 요청(Preflight request)의 캐시 시간 설정
    }

}

