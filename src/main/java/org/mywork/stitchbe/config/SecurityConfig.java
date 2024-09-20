package org.mywork.stitchbe.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.mywork.stitchbe.Util.JwtAuthenticationFilter;
import org.mywork.stitchbe.Util.JwtUtil;
import org.mywork.stitchbe.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @Author 박주희
 *
 * 2024.9.17. 박요한 | "/api/courses/**", "/api/reviews/top", "/api/home/**", "/api/search/**" permitAll 추가
 *  */

@Configuration //spring에서 빈을 정의하고 설정할 수 있는 클래스임을 의미
@EnableWebSecurity
public class SecurityConfig{

    private final  MemberDetailsService memberDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtUtil jwtUtil;

    @Autowired
    public SecurityConfig(@Lazy MemberDetailsService memberDetailsService,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          JwtUtil jwtUtil) {
        this.memberDetailsService = memberDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())  // CORS 설정 추가
                .csrf(AbstractHttpConfigurer::disable)  // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize //각 url 패턴에 대해 접근 권한 설정
                        // 로그인, 회원가입 페이지는 모든 사용자 접근 허용
                        .requestMatchers("/api/login", "/api/signup","/api/validate-email", "/api/validate-nickname", "/api/board/community/all", "/api/academies/**", "/api/courses/**", "/api/reviews/top", "/api/home/**", "/api/search/**", "/api/comments/**").permitAll()
                        // ROLE_ADMIN 권한을 가진 사용자만 admin 경로에 접근 허용
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // ROLE_USER 권한을 가진 사용자만 member 경로에 접근 허용
                        .requestMatchers("/api/member/**").hasRole("USER")
                        .requestMatchers("/api/member/community/**").permitAll()
                        // 리뷰 작성은 인증된 사용자만 가능하도록 설정(유은)
//                        .requestMatchers("/api/member/reviews/**").hasRole("USER")
//                        // 리뷰 조회는 모든 사용자에게 허용(유은)
                        .requestMatchers(HttpMethod.GET, "/api/member/reviews/**").permitAll()
                        // 리뷰 작성은 인증된 사용자만 허용(유은)
                        .requestMatchers(HttpMethod.POST, "/api/member/reviews/**").hasRole("USER")
//                         내 정보 받아오는건 인증된 사용자만 받아오도록
//                        .requestMatchers("/api/member/info").authenticated()
                        // 그 외 모든 요청은 인증된 사용자만 접근 가능
                        .anyRequest().authenticated()
                )
                // JWT 필터 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

//                .formLogin(form -> form
//                        .loginPage("/api/login")
//                        .loginProcessingUrl("/api/login")
//                        .usernameParameter("username")  // 이 값이 Vue에서 보내는 필드명과 일치해야 함
//                        .passwordParameter("password")  // 마찬가지로 password도 일치해야 함
//                        .defaultSuccessUrl("/api/home", true)
//                        .failureUrl("/api/login?error=true")
//                        .permitAll()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // 필요 시 세션 생성
//                        .maximumSessions(1)  // 하나의 세션만 유지
//                )
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
