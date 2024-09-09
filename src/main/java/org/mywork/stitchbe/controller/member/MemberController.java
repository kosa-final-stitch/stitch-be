package org.mywork.stitchbe.controller.member;
import org.mywork.stitchbe.dto.AddMemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.mywork.stitchbe.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/member/signup")
    public ResponseEntity<String> signup(@RequestBody AddMemberRequest addMemberRequest) {
        // 인스턴스를 사용하여 메서드 호출
        memberService.save(addMemberRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

}
