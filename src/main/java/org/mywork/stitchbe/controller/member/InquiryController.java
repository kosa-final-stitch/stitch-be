/*
담당자: 박요한
시작 일자: 2024.09.23
설명 : 문의사항 controller.
_____________________
2024.9.23 박요한 | 생성.
2024.9.23 박요한 | submitInquiry - memberId 조회 후 setter.
*/

package org.mywork.stitchbe.controller.member;

import java.util.List;

import org.mywork.stitchbe.dto.InquiryDTO;
import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.service.InquiryService;
import org.mywork.stitchbe.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member/inquiry")
@RequiredArgsConstructor  // Lombok을 사용한 생성자 주입
public class InquiryController {
    private final InquiryService inquiryService;
    private final MemberService memberService;

    // 사용자 문의 등록 API
    @PostMapping
    public ResponseEntity<String> submitInquiry(@RequestBody InquiryDTO inquiry, Authentication authentication) {try {
        // 로그인한 사용자의 이메일을 가져옴
        String email = authentication.getName();

        // MemberService를 통해 이메일로 memberId 조회
        Long memberId = memberService.findMemberIdByEmail(email);

        // inquiry 객체에 memberId 설정
        inquiry.setMemberId(memberId);

        // 문의 등록 처리
        inquiryService.registerInquiry(inquiry);

        return ResponseEntity.ok("문의가 성공적으로 등록되었습니다.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 등록에 실패했습니다.");
    }
    }
    
    // 로그인한 사용자의 문의 목록 조회(유은)
    @GetMapping("/{memberId}")
    public ResponseEntity<List<InquiryDTO>> getInquiryByUserId(@PathVariable Long memberId) {
    	System.out.println("(마페)문의 목록조회 컨트롤러");
        List<InquiryDTO> inquiry = inquiryService.getInquiryBymemberId(memberId);
        if (inquiry.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inquiry);
    }
   
    
}






