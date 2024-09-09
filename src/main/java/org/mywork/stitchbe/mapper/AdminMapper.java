package org.mywork.stitchbe.mapper;

import org.mywork.stitchbe.dto.AdminDto;
import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.MemberDto;

import java.util.Optional;

@Mapper
public interface AdminMapper {
    Optional<AdminDto> findByEmail(String email);
    void saveAdmin(AdminDto user);
    // 추가적인 CRUD 메서드가 필요하면 여기에 정의
}


