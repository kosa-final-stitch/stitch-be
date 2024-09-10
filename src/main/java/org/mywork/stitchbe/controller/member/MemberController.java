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

import java.util.HashMap;
import java.util.Map;

//작성자 : 박주희

@RestController
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	// 생성자를 통해 MemberService 주입
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/login")
	public String memberLoginPage() {
		return "로그인 페이지로 리다이렉트됩니다."; // 실제로는 프론트엔드에서 처리할 경로를 반환
	}

//	@GetMapping("/login")
//	public ResponseEntity<String> memberLoginPage() {
//		return ResponseEntity.ok("로그인 페이지 접근"); // 단순 메시지 반환
//	}


	// 회원가입 처리
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody AddMemberRequest addMemberRequest) {
		memberService.save(addMemberRequest);
		return ResponseEntity.ok("회원가입이 완료되었습니다.");
	}

	@PostMapping("/validate-email")
	public ResponseEntity<?> validateEmail(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		boolean isEmailValid = memberService.isEmailAvailable(email);
		System.out.println("Email received: " + email);
		Map<String, Boolean> response = new HashMap<>();
		response.put("isValid", isEmailValid);
		System.out.println("Email validation result: " + isEmailValid);
		return ResponseEntity.ok(response);
	}
	@PostMapping("/validate-nickname")
	public ResponseEntity<?> validateNickname(@RequestBody Map<String, String> request) {
		String nickname = request.get("nickname");
		System.out.println("Received nickname: " + nickname);  // 디버그용 로그
		boolean isNicknameValid = memberService.isNicknameAvailable(nickname);
		System.out.println("Nickname validation result: " + isNicknameValid);  // 결과 로그
		Map<String, Boolean> response = new HashMap<>();
		response.put("isValid", isNicknameValid);
		return ResponseEntity.ok(response);
	}


	// 특정 회원 정보 조회 API
	@GetMapping("/info/{memberId}")
	public MemberDto getMemberInfo(@PathVariable Long memberId) {
		return memberService.getMemberInfo(memberId); // MemberService를 통해 회원 정보 가져옴
	}
}