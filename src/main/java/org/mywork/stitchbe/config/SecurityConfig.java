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
<<<<<<< HEAD
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

import java.util.Collections;
import java.util.Map;
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
>>>>>>> 1933334939ac4b7a886e1e0104675b60e38c2132


/**
 * @Author 박주희
 *
 * 2024.9.17.~24. 박요한 | "/api/courses/**", "/api/reviews/top", "/api/home/**", "/api/search/**", "api/payment/**" permitAll 추가
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
                                "/api/member/reviews/top", "/api/home/**", "/api/search/**"," /api/comments/**", "api/payment/**").permitAll()
                        // ROLE_ADMIN 권한을 가진 사용자만 admin 경로에 접근 허용
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // ROLE_USER 권한을 가진 사용자만 member 경로에 접근 허용
                        .requestMatchers("/api/member/**").hasAnyRole("USER","ADMIN") //(관리자 권한 추가 호영수정)
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

<<<<<<< HEAD
                // OAuth2 로그인 설정 추가
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/api/login/oauth2/code/google?state=PR7lPnYjh42F7GgmiNnv2jKvDoK7JOTxC7Bw_NkHAe0%3D&code=4%2F0AQlEd8ycbeu4XP_6IUyRjBYpBSXuyQvsto5Lf6Ojn1rOuGdtuV0zmJlnN_coqyRK5owFhw&scope=email+profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none")
                        .defaultSuccessUrl("/api/home", true)
                        .failureUrl("/api/login?error=true")
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(oidcUserService()) // OIDC를 위한 OidcUserService 설정
                        )
                )
=======
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
>>>>>>> 1933334939ac4b7a886e1e0104675b60e38c2132
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .logoutSuccessUrl("/api/login")
                )
                .userDetailsService(memberDetailsService);
        return http.build();
    }

<<<<<<< HEAD
    // OIDC 사용자 정보를 처리하는 OidcUserService 정의
    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        OidcUserService delegate = new OidcUserService();
        return (userRequest) -> {
            // OidcUser 객체를 기본 서비스로부터 로드
            OidcUser oidcUser = delegate.loadUser(userRequest);

            // 사용자 정보를 처리하는 커스텀 로직 추가
            Map<String, Object> attributes = oidcUser.getAttributes();

            // 예를 들어, 사용자 정보를 데이터베이스에 저장하거나 추가적인 정보로 커스터마이징할 수 있습니다.

            // 새로운 사용자 정보를 반환
            return new DefaultOidcUser(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    oidcUser.getIdToken(),
                    oidcUser.getUserInfo(),
                    "sub" // 사용자 이름 속성 (필요에 따라 변경 가능)
            );
        };
    }
=======
>>>>>>> 1933334939ac4b7a886e1e0104675b60e38c2132


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
