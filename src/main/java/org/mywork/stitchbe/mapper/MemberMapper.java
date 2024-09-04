package org.mywork.stitchbe.mapper;

import org.mywork.stitchbe.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM users WHERE email = #{email}")
    Optional<MemberDto> findByEmail(String email);
    void saveMember(MemberDto user);
    // 추가적인 CRUD 메서드가 필요하면 여기에 정의
}
