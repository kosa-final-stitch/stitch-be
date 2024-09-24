/*
담당자: 박요한
시작 일자: 2024.09.22
설명 : 문의사항 mapper.
_____________________
2024.9.22 박요한 | 생성.
2024.9.24 김호영 | 문의 사항 조회 답변 등록.
*/

package org.mywork.stitchbe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.InquiryDTO;

import java.util.List;

@Mapper
public interface InquiryMapper {
    // 문의 등록 메서드
    void insertInquiry(InquiryDTO inquiry);

    // 모든 문의사항 조회 (호영)
    List<InquiryDTO> findInquiriesByMemberId(Long memberId);

    // 문의 사항 답변 등록 (호영)
    void updateInquiryAnswer(Long inquiryId, Long adminId, String answer);
}
