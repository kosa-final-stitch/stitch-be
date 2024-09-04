package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.AddMemberRequest;
import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddMemberRequest dto) {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(dto.getEmail());
        memberDto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        // MemberDto를 저장하고 그 결과로 memberId를 반환
        memberMapper.saveMember(memberDto);
        return memberDto.getMemberId(); // MyBatis에서는 INSERT 후에 자동으로 생성된 ID를 가져올 수 있습니다.
    }
}
