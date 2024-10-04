package org.mywork.stitchbe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//작성자 : 박주희
//2024.9.24. 박요한 | 로컬 IP 추가

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) { //클라이언트와 서버가 서로 다른 도메인에 있을 때, 클라이언트가 서버에 요청을 보낼 수 있도록 허용
        // CORS 설정
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081", "http://192.168.210.215:8081/", "http://192.168.220.13:8081")  // Vue.js의 포트를 정확히 명시
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }


    // 다른 MVC 설정 (예: 인터셉터 설정)도 여기에 추가 가능
}