package org.mywork.stitchbe.config;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.service.MemberDetailsService;
import org.mywork.stitchbe.service.AdminDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

//작성자 : 박주희

@RequiredArgsConstructor //lombok에서 제공. 'final'로 선언된 모든 필드에 대해 생성자를 자동으로 생성
//memberDetailsService와 adminDetailsService 필드를 생성자 주입을 통해 초기화할 수 있게 해줌
@Configuration //spring에서 빈을 정의하고 설정할 수 있는 클래스임을 의미
public class SecurityConfig {

    private final @Lazy MemberDetailsService memberDetailsService;
    private final @Lazy AdminDetailsService adminDetailsService;//해당 빈이 실제로 필요할 때까지 초기화를 지연시킴

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //특정 요청 경로를 보안 필터링에서 제외
        return (web) -> web.ignoring()
                .requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize //각 url 패턴에 대해 접근 권한 설정
                        .requestMatchers("/member/login", "/member/signup").permitAll()
                        .requestMatchers("/admin/login", "/admin/signup").permitAll()
                        .requestMatchers("/member/**").hasRole("USER") //user 역할 가진 사용자만 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() //나머지 모든 요청은 인증된 사용자만 접근 가능
                )
                .formLogin(form -> form //폼 user 로그인 설정
                        .loginPage("/member/login") //해당 경로 페이지를 로그인 페이지로 사용
                        .loginProcessingUrl("/member/login") // 회원 로그인 처리 URL
                        .defaultSuccessUrl("/member/home") //로그인 성공 시 해당 경로로 redirect
                        .permitAll() //로그인 페이지에 누구나 접근 가능
                )
                .formLogin(form -> form //관리자용
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login") // 관리자 로그인 처리 URL
                        .defaultSuccessUrl("/admin/home")
                        .permitAll()
                )
                .logout(logout -> logout //user, admin 동일
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login") //로그아웃 후 해당 페이지로 리다이렉트
                        .invalidateHttpSession(true) //로그아웃 시 세션을 무효화하여 모든 세션 정보 삭제
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/admin/login"),
                                request -> request.getRequestURI().startsWith("/admin")
                        )
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/member/login"),
                                request -> request.getRequestURI().startsWith("/member")
                        )
                )
                .csrf(csrf -> csrf.disable()); //csrf 보호를 비활성화

        return http.build(); //설정이 완료된 HttpSecurity 객체를 빌드하여 반환.
        //이로써 spring security 필터 체인 완성
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //시큐리티에서 인증을 관리하는 authenticationManager를 빈으로 등록
        // 인증 요청을 처리할 때 사용
    }
}
