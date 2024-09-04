package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.AdminDto;
import org.mywork.stitchbe.mapper.AdminMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

//작성자 : 박주희

@Service
public class AdminDetailsService {

    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminDetailsService(@Lazy BCryptPasswordEncoder passwordEncoder, AdminMapper adminMapper) {
        this.passwordEncoder = passwordEncoder;
        this.adminMapper = adminMapper;
    }

    // 기존의 관리자 인증 메서드 유지
    public AdminDto authenticateAdmin(String adminId, String password) {
        AdminDto adminDto = adminMapper.findByAdminId(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with admin_id: " + adminId));

        if (!passwordEncoder.matches(password, adminDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return adminDto;
    }

    // UserDetails 반환하는 메서드 추가
    public UserDetails loadUserByAdminId(String adminId) {
        AdminDto adminDto = adminMapper.findByAdminId(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with admin_id: " + adminId));

        return new User(
                adminDto.getAdmin_id().toString(),
                adminDto.getPassword(),
                Collections.emptyList() // 권한 리스트 설정 (필요 시 추가)
        );
    }
}
