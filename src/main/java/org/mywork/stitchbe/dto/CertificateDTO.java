/*
담당자: 김호영
시작 일자: 2024.09.29
설명 : 수료과목 인증  dto
---------------------
2024.09.29 김호영 | 수료 과목 인증 dto 생성.
*/


package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CertificateDTO {
    private Long certificateId;    // 수료증 ID
    private Long memberId;         // 회원 ID
    private Long courseId;         // 과정 ID
    private String courseName;     // 수료 과정명 (조인)
    private String academyName;    // 학원명 (조인)
    private String nickname;       // 회원 닉네임 (조인으로 가져옴)
    private Date completionDate;   // 수료 날짜
    private String status;         // 상태 ('미처리', '인증', '불가')
    private String filename;       // 수료증 파일명
}