/*
담당자: 박요한
시작 일자: 2024.09.23
설명 : 문의사항 service.
_____________________
2024.9.23 박요한 | 생성.
*/

package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.InquiryDTO;
import org.mywork.stitchbe.mapper.InquiryMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 의존성 자동 주입
public class InquiryService {
    private final InquiryMapper inquiryMapper;

    // 문의 등록 서비스 메서드
    public void registerInquiry(InquiryDTO inquiry) {
        try {
            inquiryMapper.insertInquiry(inquiry);
        } catch (Exception e) {
            // 예외 처리 로직 추가 (로그, 사용자 알림 등)
            throw new RuntimeException("문의 등록에 실패했습니다.", e);
        }
    }

}
