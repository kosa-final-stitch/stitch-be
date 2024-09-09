package org.mywork.stitchbe.config;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.service.MemberDetailsService;
import org.mywork.stitchbe.service.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

//@Author : 박주희
/**
 * @Author 박주희
 *
 *  */
 //lombok에서 제공. 'final'로 선언된 모든 필드에 대해 생성자를 자동으로 생성
//memberDetailsService와 adminDetailsService 필드를 생성자 주입을 통해 초기화할 수 있게 해줌

@Configuration //spring에서 빈을 정의하고 설정할 수 있는 클래스임을 의미
@EnableWebSecurity
public class SecurityConfig{

    private final  MemberDetailsService memberDetailsService;
    private final  AdminDetailsService adminDetailsService;//해당 빈이 실제로 필요할 때까지 초기화를 지연시킴

    @Autowired
    public SecurityConfig(@Lazy AdminDetailsService adminDetailsService, MemberDetailsService memberDetailsService) {
        this.adminDetailsService = adminDetailsService;
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
    @Order(1)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
        .cors(withDefaults())  // CORS 설정 추가
        .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize //각 url 패턴에 대해 접근 권한 설정
                        .requestMatchers("/member/login", "/member/signup").permitAll()
                        .requestMatchers("/admin/login", "/admin/signup").permitAll()
                        .requestMatchers("/member/**").hasRole("USER") //user 역할 가진 사용자만 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() //나머지 모든 요청은 인증된 사용자만 접근 가능
                )
                .formLogin(form -> form
                        .loginPage("/api/admin/login")
                        .loginProcessingUrl("/api/admin/loginProcess")
                        .defaultSuccessUrl("/api/admin/home")
                        .failureUrl("/api/admin/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/api/admin/logout")
                        .logoutSuccessUrl("/api/admin/login")
                )
                .userDetailsService(adminDetailsService);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain memberFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())  // CORS 설정 추가
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .securityMatcher("/api/member/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // 모든 OPTIONS 요청 허용
                        .requestMatchers("/api/member/login","/api/member/signup").permitAll()
                        .requestMatchers("/api/member/info/**").permitAll()//유은 일시삽입(데이터 가져오기용)
                        .anyRequest().hasRole("USER")
                )
                .formLogin(form -> form
                        .loginPage("/api/member/login")
                        .loginProcessingUrl("/api/member/loginProcess")
                        .defaultSuccessUrl("/api/member/home")
                        .failureUrl("/api/member/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/api/member/logout")
                        .logoutSuccessUrl("/api/member/login")
                )
                .userDetailsService(memberDetailsService);

        return http.build();
    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
