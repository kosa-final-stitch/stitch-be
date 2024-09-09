package org.mywork.stitchbe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//작성자 : 박주희

@Configuration
@EnableWebMvc //spring mvc 기능 활성화
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //정적 자원의 요청 경로와 실제 자원 위치를 매핑
        // 정적 리소스 파일 처리
        registry.addResourceHandler("/static/**") //해당 경로로 시작하는 모든 요청을 처리하도록 서정
                .addResourceLocations("classpath:/static/"); //클래스패스내의 해당 디렉토리에서 정적 자원을 찾도록 지정
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) { //뷰 리졸버 설정.
        // 컨트롤러에서 반환되는 뷰 이름을 실제 뷰 파일로 매핑하는 역할
        // 뷰 리졸버 설정 (예: Thymeleaf, JSP)
        registry.jsp("/WEB-INF/views/", ".jsp"); //뷰 파일이 저장된 기본경로 / 사용되는 뷰 파일의 확장자
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) { //클라이언트와 서버가 서로 다른 도메인에 있을 때, 클라이언트가 서버에 요청을 보낼 수 있도록 허용
        // CORS 설정
        registry.addMapping("/**") //모든 경로에 대해 CORS 설정 적용
                .allowedOrigins("http://localhost:8081") //해당 경로에서 오는 요청만 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE") //해당 메서드 허용
                .allowCredentials(true); //자격 증명(쿠키, 인증 헤더...) 허용
    }

    // 다른 MVC 설정 (예: 인터셉터 설정)도 여기에 추가 가능
}
