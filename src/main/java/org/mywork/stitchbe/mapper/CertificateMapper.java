/*
담당자: 김호영
시작 일자: 2024.09.29
설명 : 수료과목 인증 Mapper
---------------------
2024.09.29 김호영 | 수료 과목 인증 insert, select update mapper 생성.
2024.10.03 김호영 | 교육명, 학원명, 회차 정보로 course_id 조회 구현
*/

package org.mywork.stitchbe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.CertificateDTO;

import java.util.List;

@Mapper
public interface CertificateMapper {

    // 수료증 등록
    void insertCertificate(CertificateDTO certificate);

    // 로그인된 사용자의 수료증 항목 조회
    List<CertificateDTO> selectCertificatesByMemberId(Long memberId);

    // 모든 수료 항목 조회 (관리자용)
    List<CertificateDTO> selectAllCertificates();

    void updateCertificateStatus(Long certificateId, String status);

    // courseId 조회
    Long findCourseIdByDetails(String courseName, String academyName, int sessionNumber);
}