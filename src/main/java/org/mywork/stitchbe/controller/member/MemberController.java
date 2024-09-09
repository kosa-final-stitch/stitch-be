package org.mywork.stitchbe.controller.member;

import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	// 특정 회원 정보 조회 API
	@GetMapping("/info/{memberId}")
	public MemberDto getMemberInfo(@PathVariable Long memberId) {
		return memberService.getMemberInfo(memberId); // MemberService를 통해 회원 정보 가져옴
	}
}
