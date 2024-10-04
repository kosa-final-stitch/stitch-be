/*
담당자: 박요한
시작 일자: 2024.09.22
설명 : 문의사항 mapper.
_____________________
2024.9.22 박요한 | 생성.
2024.9.24 김호영 | 문의 사항 조회 답변 등록.
*/

package org.mywork.stitchbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.InquiryDTO;

import java.util.List;

@Mapper
public interface InquiryMapper {
    // 문의 등록 메서드
    void insertInquiry(InquiryDTO inquiry);
    
    // 로그인한 사용자의 문의 목록 조회(유은)
	List<InquiryDTO> getInquiryBymemberId(@Param("memberId") Long memberId);

    // 모든 문의사항 조회 (호영)
    List<InquiryDTO> findAllInquiries();

    // 문의 사항 답변 등록 (호영)
    void updateInquiryAnswer(Long inquiryId, Long adminId, String answer);

    // 특정 문의 상세 조회 API(유은)
	InquiryDTO findInquiryById(Long inquiryId);

	
}
