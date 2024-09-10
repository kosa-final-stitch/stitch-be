package org.mywork.stitchbe.mapper;
import org.mywork.stitchbe.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

//작성자 : 박주희

@Mapper
public interface MemberMapper {

    Optional<MemberDto> findByEmail(String email);

    void saveMember(MemberDto user);

    MemberDto getMemberById(Long memberId);

    // 권한 저장 메서드
    void saveUserRole(String email, String authority);

    List<String> findAuthoritiesByEmail(String email);


}
