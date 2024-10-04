/*
담당자: 박요한
시작 일자: 2024.09.23
설명 : 문의사항 service.
_____________________
2024.9.23 박요한 | 생성.
2024.9.24 김호영 | 문의사항 조회 답변 등록.
*/

package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.mywork.stitchbe.dto.InquiryDTO;
import org.mywork.stitchbe.mapper.InquiryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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


    // 로그인한 사용자의 문의 목록 조회(유은)
	public List<InquiryDTO> getInquiryBymemberId(Long memberId) {
		List<InquiryDTO> inquries = inquiryMapper.getInquiryBymemberId(memberId);	   	
		return inquries;
		  
	}
	

    // 특정 문의 상세 조회 API(유은)
	public InquiryDTO getInquiryById(Long inquiryId) {
	 	System.out.println("마페 문의디테일 서비스");
	    return inquiryMapper.findInquiryById(inquiryId); // MyBatis 매퍼에서 데이터 조회
	    }


    // 모든 사용자의 문의사항 조회
    public List<InquiryDTO> getAllInquiries() {
        return inquiryMapper.findAllInquiries();  // 모든 문의사항 조회
    }
    
    // 문의사항 답변 등록 (호영)
    public void submitAnswer(Long inquiryId, Long adminId, String answer) {
        try {
            // 답변 등록 로직
            inquiryMapper.updateInquiryAnswer(inquiryId, adminId, answer);
        } catch (Exception e) {
            throw  new RuntimeException("답변 등록 실패.", e);
        }
    }

}
