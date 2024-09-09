package org.mywork.stitchbe.controller.member;

import org.mywork.stitchbe.dto.AddMemberRequest;
import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;

	// 생성자를 통해 MemberService 주입
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/member/login")
	public String memberLoginPage() {
		return "member/login";
	}

	@PostMapping("/api/member/signup")
	public ResponseEntity<String> signup(@RequestBody AddMemberRequest addMemberRequest) {
		// 인스턴스를 사용하여 메서드 호출
		memberService.save(addMemberRequest);
		return ResponseEntity.ok("회원가입이 완료되었습니다.");
	}

	// 특정 회원 정보 조회 API
	@GetMapping("/info/{memberId}")
	public MemberDto getMemberInfo(@PathVariable Long memberId) {
		return memberService.getMemberInfo(memberId); // MemberService를 통해 회원 정보 가져옴
	}
}