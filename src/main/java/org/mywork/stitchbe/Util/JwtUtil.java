package org.mywork.stitchbe.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class); // Logger 추가

    @Value("${jwt.secret}")
    private String SECRET_KEY; // 비밀 키

    private final long EXPIRATION_TIME = 3600000; // 1시간

    public String generateToken(Authentication authentication) {
        try {
            // 사용자 이름과 권한을 가져옴
            String username = authentication.getName();
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            // 권한 목록을 문자열로 변환
            String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            // JWT 토큰 생성
            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("roles", authorities)
                    .setIssuedAt(now)  // 발급 시간 설정
                    .setExpiration(new Date(nowMillis + EXPIRATION_TIME)) // 만료 시간 설정
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            // 생성된 토큰 로그 출력
            logger.info("Generated JWT Token: {}", token);

            return token;
        } catch (Exception e) {
            // 예외 발생 시 로그로 에러 출력
            logger.error("Error occurred while generating JWT token", e);
            throw new RuntimeException("JWT 토큰 생성 중 에러 발생");
        }
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
