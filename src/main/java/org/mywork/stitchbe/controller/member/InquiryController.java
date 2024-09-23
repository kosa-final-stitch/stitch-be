/*
담당자: 박요한
시작 일자: 2024.09.23
설명 : 문의사항 controller.
_____________________
2024.9.23 박요한 | 생성.
*/

package org.mywork.stitchbe.controller.member;

import org.mywork.stitchbe.dto.InquiryDTO;
import org.mywork.stitchbe.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member/inquiry")
@RequiredArgsConstructor  // Lombok을 사용한 생성자 주입
public class InquiryController {
    private final InquiryService inquiryService;

    // 사용자 문의 등록 API
    @PostMapping
    public ResponseEntity<String> submitInquiry(@RequestBody InquiryDTO inquiry) {
        try {
            inquiryService.registerInquiry(inquiry);
            return ResponseEntity.ok("문의가 성공적으로 등록되었습니다.");  // 성공 시 200 OK 응답
        } catch (Exception e) {
            // 예외 발생 시 500 Internal Server Error 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 등록에 실패했습니다.");
        }
    }
}






