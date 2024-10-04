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

    boolean existsByEmail(String email);  // 이메일 중복 검사 메서드

    boolean findByNickname(String nickname);

    // 이메일로 memberId 조회
    Long findMemberIdByEmail(String email);

    //유은작성 코드
    
    //마이페이지 사용자
    MemberDto getMemberByEmail(String email);
	
    //내 정보 업데이트
    void updateMemberInfo(MemberDto memberDto);


    // 모든 회원 조회 (호영)
    List<MemberDto> getAllMembers();

    // 회원 삭제 (호영)
    void deleteMemberByEmail(String email);
}
