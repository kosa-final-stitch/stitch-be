/*
담당자: 박요한
시작 일자: 2024.09.22
설명 : 문의사항 dto.
_____________________
2024.9.22 박요한 | 생성.
*/

package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

@Data
public class InquiryDTO {
    private Long inquiryId;    // 문의 ID (PK)
    private Long memberId;     // 작성자 ID
    private Long adminId;      // 답변자 (관리자) ID, nullable
    private String category;   // 문의 유형
    private String title;      // 문의 제목
    private String content;    // 문의 내용
    private String answer;     // 답변 내용, nullable
    private String status;     // 문의 상태 (예: 처리 중, 완료 등)
    private Date regDate;      // 문의 등록일
    private Date ansDate;      // 답변 등록일, nullable
    private String memberName; // 질문자 이름, join
    private String adminName;  // 답변자 이름, nullable, join
}
