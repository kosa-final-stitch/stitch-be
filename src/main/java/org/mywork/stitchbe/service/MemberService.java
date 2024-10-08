package org.mywork.stitchbe.service;

import java.util.List;

import org.mywork.stitchbe.dto.AddMemberRequest;
import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

//작성작 : 박주희
// 24.09.23 박요한 | findMemberIdByEmail 추가

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberMapper memberMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public Long save(AddMemberRequest dto) {
		if (memberMapper.findByEmail(dto.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email already exists");
		}

		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(dto.getEmail());
		memberDto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		memberDto.setOldPassword(bCryptPasswordEncoder.encode(dto.getPassword())); // Old Password도 설정
		memberDto.setNickname(dto.getNickname());
		memberDto.setName(dto.getName());
		memberDto.setAddress(dto.getAddress());
		memberDto.setGender(dto.getGender());
		// 로그로 전달된 성별 확인
		System.out.println("전달된 성별 값: " + dto.getGender());

		memberDto.setBirth(dto.getBirth());
		memberDto.setPhone(dto.getPhone());

		// 로그 추가: 회원 정보를 저장하기 전에 출력
		System.out.println("저장할 회원 정보: " + memberDto);

		// MemberDto를 저장하고 그 결과로 memberId를 반환
		memberMapper.saveMember(memberDto);

		// 로그 추가: 회원 저장 후 ID 출력
		System.out.println("저장된 회원 ID: " + memberDto.getMemberId());

		// AUTH 테이블에 기본 권한 저장 (ROLE_USER)
		memberMapper.saveUserRole(memberDto.getEmail(), "ROLE_USER");

		// 로그 추가: 권한 저장 완료 메시지
		System.out.println("회원 권한 저장 완료: " + memberDto.getEmail() + "에게 ROLE_USER 권한 부여");

		return memberDto.getMemberId(); // MyBatis에서는 INSERT 후에 자동으로 생성된 ID를 가져올 수 있습니다.
	}

	// 로그인 로직 추가
	public boolean login(String email, String password) {
		MemberDto member = memberMapper.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Email not found"));

		// 입력된 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(password, member.getPassword());

		// 로그 출력
		System.out.println("입력된 비밀번호: " + password);
		System.out.println("DB에 저장된 암호화된 비밀번호: " + member.getPassword());
		System.out.println("비밀번호 비교 결과: " + isPasswordMatch);

		return login(email, password);
	}

	// 이메일 중복 확인
	public boolean isEmailAvailable(String email) {
		return !memberMapper.existsByEmail(email);
	}

	// 닉네임 중복 여부 확인
	public boolean isNicknameAvailable(String nickname) {
		boolean result = memberMapper.findByNickname(nickname);
		return !result;
	}

	// 유은작성코드

	// 회원 정보 조회
//  public MemberDto getMemberInfo(Long memberId) {
//  	System.out.println("서비스의 memberID"+memberId);
//      return memberMapper.getMemberById(memberId); // MyBatis mapper를 통해 DB에서 회원 정보를 가져옴
//  }

	// 회원 정보 조회
	public MemberDto getMemberInfoByEmail(String email) {
		System.out.println("이메일로 회원 정보 조회: " + email);
		return memberMapper.getMemberByEmail(email); // MyBatis Mapper를 통해 DB에서 회원 정보를 가져옴
	}

	//	회원 정보 업데이트
	public void updateMemberInfo(String email, MemberDto memberDto) {
		memberDto.setEmail(email); // 이메일은 수정하지 않으므로 그대로 설정
		memberMapper.updateMemberInfo(memberDto);
	}



	// 이메일을 통해 memberId를 조회하는 메서드 (요한)
	public Long findMemberIdByEmail(String email) {
		return memberMapper.findMemberIdByEmail(email);

	}

	// 호영 작성 코드
	// 모든 회원 조회 (호영)
	public List<MemberDto> getAllMembers() {
		return memberMapper.getAllMembers();
	}

		// 사용자 정보 삭제(호영)
		public void deleteByEmail (String email){
			memberMapper.deleteMemberByEmail(email);
		}

}
