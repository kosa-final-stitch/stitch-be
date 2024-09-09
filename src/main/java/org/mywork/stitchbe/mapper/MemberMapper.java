package org.mywork.stitchbe.mapper;

import org.mywork.stitchbe.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberDto> findByEmail(String email);
    void saveMember(MemberDto user);
    
    // 추가적인 CRUD 메서드가 필요하면 여기에 정의
    

    // 특정 회원 정보 조회
    @Select("SELECT * FROM member WHERE member_id = #{memberId}")
    MemberDto getMemberById(Long memberId);
}
