package org.mywork.stitchbe.config;

import jakarta.servlet.http.HttpServletResponse;
import org.mywork.stitchbe.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;


/**
 * @Author 박주희
 *
 *  */

@Configuration //spring에서 빈을 정의하고 설정할 수 있는 클래스임을 의미
@EnableWebSecurity
public class SecurityConfig{

    private final  MemberDetailsService memberDetailsService;

    @Autowired
    public SecurityConfig(@Lazy MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() { //특정 요청 경로를 보안 필터링에서 제외
//        return (web) -> web.ignoring()
//                .requestMatchers("/static/**");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())  // CORS 설정 추가
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize //각 url 패턴에 대해 접근 권한 설정
                        // 로그인, 회원가입 페이지는 모든 사용자 접근 허용
                        .requestMatchers("/api/login", "/api/signup","/api/loginProcess","/api/validate-email", "/api/validate-nickname").permitAll()
                        // ROLE_ADMIN 권한을 가진 사용자만 admin 경로에 접근 허용
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // ROLE_USER 권한을 가진 사용자만 member 경로에 접근 허용
                        .requestMatchers("/api/member/**").hasRole("USER")
                        // 그 외 모든 요청은 인증된 사용자만 접근 가능
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/api/login")
                        .loginProcessingUrl("/api/loginProcess")
//                        .successHandler((request, response, authentication) -> {
//                            // 로그인 성공 시 JSON 응답
//                            response.setStatus(HttpServletResponse.SC_OK);
//                            response.getWriter().write("{\"message\": \"로그인 성공\"}");
//                        })//연결 후 지울것
//                        .failureHandler((request, response, exception) -> {
//                            // 로그인 실패 시 JSON 응답
//                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                            response.getWriter().write("{\"error\": \"로그인 실패\"}");
//                        })//연결 후 지울 것
                        .defaultSuccessUrl("/api/login", true)
                        .failureUrl("/api/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .logoutSuccessUrl("/api/login")
                )
                .userDetailsService(memberDetailsService);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
