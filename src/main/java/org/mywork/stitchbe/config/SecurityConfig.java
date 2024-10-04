package org.mywork.stitchbe.config;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author 박주희
 *
 * 2024.9.17.~24. 박요한 | "/api/courses/**", "/api/reviews/top", "/api/home/**", "/api/search/**", "api/payment/**" permitAll 추가
 * 2024.10.4. 박요한 | "/api/member/reviews/**" 위치 이동.
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
                                .requestMatchers("/api/login", "/api/signup","/api/validate-email",
                                        "/api/validate-nickname", "/api/board/community/all", "/api/academies/**", "/api/courses/**",
                                        "/api/member/reviews/top", "/api/home/**",
                                        "/api/search/**"," /api/comments/**", "api/payment/**", "/api/oauth2/login").permitAll()
                                // 리뷰 조회는 모든 사용자에게 허용(유은)(요한 재배치)
                                .requestMatchers(HttpMethod.GET, "/api/member/reviews/**").permitAll()
                                // ROLE_ADMIN 권한을 가진 사용자만 admin 경로에 접근 허용
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                // ROLE_USER 권한을 가진 사용자만 member 경로에 접근 허용
                                .requestMatchers("/api/member/**").hasAnyRole("USER","ADMIN",  "OAUTH2_USER") //(관리자 권한 추가 호영수정)
                                .requestMatchers("/api/member/community/**").permitAll()
                                // 리뷰 작성은 인증된 사용자만 가능하도록 설정(유은)
//                        .requestMatchers("/api/member/reviews/**").hasRole("USER")
                                // 리뷰 작성은 인증된 사용자만 허용(유은)
                                .requestMatchers(HttpMethod.POST, "/api/member/reviews/**").hasRole("USER")
//                         내 정보 받아오는건 인증된 사용자만 받아오도록
//                        .requestMatchers("/api/member/info").authenticated()
                                // 그 외 모든 요청은 인증된 사용자만 접근 가능
                                .anyRequest().authenticated()
                )
                // JWT 필터 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // 필요 시 세션 생성
//                        .maximumSessions(1)  // 하나의 세션만 유지
//                )
                // OAuth2 로그인 설정
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/api/oauth2/login")
//                        .defaultSuccessUrl("/api/home", true)
//                        .failureUrl("/api/oauth2/login?error=true")
//                        .authorizationEndpoint(authorization -> authorization
//                                .baseUri("/oauth2/authorization")  // OAuth2 인증 시작 경로 설정
//                        )
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(new DefaultOAuth2UserService())  // 기본 제공 OAuth2UserService 사용
//                        )
//                        .successHandler((request, response, authentication) -> {
//                            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//                            // 구글로부터 받은 사용자 정보를 처리하는 로직을 여기에 작성
//                            String email = oAuth2User.getAttribute("email");
//
//                            // 사용자 권한 처리
//                            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
//
//                            // JWT 생성
//                            String jwtToken = jwtUtil.generateToken(authentication);  // Authentication 객체 전달
//
//                            // JWT를 쿠키에 저장
//                            Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
//                            jwtCookie.setHttpOnly(true);  // HttpOnly 속성 추가
//                            jwtCookie.setMaxAge(60 * 60 * 10);  // 쿠키 유효 기간 10시간 설정
//                            jwtCookie.setPath("/");
//
//                            // 쿠키를 응답에 추가
//                            response.addCookie(jwtCookie);
//
//                            // 성공 후 리다이렉션
//                            response.sendRedirect("/");
//                        })
//                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(this::handleOAuth2LoginSuccess) // 커스텀 성공 핸들러 설정
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .logoutSuccessUrl("/api/login")
                )
                .userDetailsService(memberDetailsService);
        return http.build();
    }

    private void handleOAuth2LoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 사용자 정보 가져오기
        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");  // 이메일 정보 가져오기
        String name = user.getAttribute("name");  // 이름 정보 가져오기

        // OAuth2 사용자에게 ROLE_USER 권한을 추가
        // OAuth2 사용자에게 ROLE_USER 권한을 추가하기 위해 authorities를 수정 가능한 리스트로 변환
        Collection<? extends GrantedAuthority> currentAuthorities = authentication.getAuthorities();
        List<GrantedAuthority> authorities = currentAuthorities.stream()
                .collect(Collectors.toList());  // 수정 가능한 리스트로 변환
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));  // ROLE_USER 권한 추가

        // 사용자 정보를 로그에 출력
        System.out.println("User Email: " + email);
        System.out.println("User Name: " + name);
        authorities.forEach(authority -> System.out.println("Role: " + authority.getAuthority()));

        // 권한 정보를 문자열로 변환
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // JWT 생성 - 권한 정보를 포함
        String jwtToken = jwtUtil.generateToken(authentication);  // 기존 generateToken 사용

        // JWT 토큰을 응답 헤더에 추가 (필요 시 사용)
        response.addHeader("Authorization", "Bearer " + jwtToken);

        // JWT를 프론트엔드로 전달 (쿼리 파라미터로 전달)
        try {
            response.sendRedirect("http://localhost:8081/?token=" + jwtToken + "&roles=" + roles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
