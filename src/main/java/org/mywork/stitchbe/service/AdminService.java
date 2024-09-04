package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.AddAdminRequest;
import org.mywork.stitchbe.dto.AdminDto;
import org.mywork.stitchbe.mapper.AdminMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddAdminRequest dto) {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdmin_id(dto.getAdmin_id());
        adminDto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        adminDto.setRole("ROLE_ADMIN");
        adminDto.setStatus("ACTIVE"); // 예: 기본 상태 설정

        adminMapper.saveAdmin(adminDto);
        return adminDto.getAdmin_id();  // INSERT 후에 자동으로 생성된 ID를 반환
    }
}
