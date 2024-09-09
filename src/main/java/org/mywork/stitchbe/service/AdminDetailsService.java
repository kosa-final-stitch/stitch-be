package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.AdminDto;
import org.mywork.stitchbe.mapper.AdminMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

//작성자 : 박주희

@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminDetailsService(@Lazy BCryptPasswordEncoder passwordEncoder, AdminMapper adminMapper) {
        this.passwordEncoder = passwordEncoder;
        this.adminMapper = adminMapper;
    }

    // 기존의 관리자 인증 메서드 유지
    public AdminDto authenticateAdmin(String email, String password) {
        AdminDto adminDto = adminMapper.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with email: " + email));

        if (!passwordEncoder.matches(password, adminDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return adminDto;

    }

    // UserDetailsService 인터페이스의 메서드 구현
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {;
        AdminDto adminDto = adminMapper.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email));

        return new User(
                adminDto.getEmail(), // Long을 String으로 변환
                adminDto.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(adminDto.getRole())) // 권한 리스트 설정 (필요 시 추가)
        );
    }
}
