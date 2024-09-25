/*
담당자: 박요한
시작 일자: 2024.09.24
설명 : 결제 controller.
_____________________
2024.9.24 박요한 | 생성.
2024.9.25 김호영 | 결제 정보 확인 구현.
*/

package org.mywork.stitchbe.controller.member;

import org.mywork.stitchbe.dto.PaymentDTO;
import org.mywork.stitchbe.service.MemberService;
import org.mywork.stitchbe.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor  // Lombok을 사용한 생성자 주입
public class PaymentController {
    private final PaymentService paymentService;
    private final MemberService memberService;

    @PostMapping("/payment/complete")
    public ResponseEntity<String> completePayment(@RequestBody PaymentDTO paymentDTO, Authentication authentication) {
        try {
            String email;

            // 로그인 여부 확인
            if (authentication != null && authentication.isAuthenticated()) {
                // 로그인된 사용자의 이메일 가져오기
                email = authentication.getName();
            } else {
                // 비회원일 경우 anonymous 사용자의 이메일 설정
                email = "anonymous@domain.com";
            }

            // email로 memberId 조회
            Long memberId = memberService.findMemberIdByEmail(email);
            paymentDTO.setMemberId(memberId);  // PaymentDTO에 memberId 설정

            // 결제 서비스 호출
            paymentService.processPayment(paymentDTO);

            return ResponseEntity.ok("결제 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 처리 중 오류 발생");
        }
    }

    // 전체 결제 정보 조회 (관리자용)
    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }



}





















