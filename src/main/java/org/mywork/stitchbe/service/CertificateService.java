/*
담당자: 김호영
시작 일자: 2024.09.29
설명 : 수료과목 인증 Service
---------------------
2024.09.29 김호영 | 수료 과목 인증 조회 등록 수정 Service 생성.
2024.10.03 김호영 | 교육명, 학원명, 회차 정보로 course_id 조회 구현.
*/

package org.mywork.stitchbe.service;

import org.mywork.stitchbe.dto.CertificateDTO;
import org.mywork.stitchbe.mapper.CertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class CertificateService {

    @Autowired
    private CertificateMapper certificateMapper;

    @Autowired
    private S3Service s3Service;  // S3Service 주입

    // 수료인증 등록
    public void registerCertificate(String courseName, String academyName, Date completionDate,
                                    String status, MultipartFile file, Long memberId, Long courseId) {
        CertificateDTO certificate = new CertificateDTO();
        certificate.setCourseName(courseName);
        certificate.setAcademyName(academyName);
        certificate.setCompletionDate(completionDate); // Date 형식으로 전달받아 저장
        certificate.setStatus(status);
        certificate.setMemberId(memberId); // 추가: 로그인된 회원의 ID 설정
        certificate.setCourseId(courseId); // 추가: 과정 ID 설정

        // S3에 파일 업로드 및 파일 이름 반환
        String savedFileName = s3Service.uploadFile(file);
        certificate.setFilename(savedFileName);  // S3에 저장된 파일명 설정

        // DB에 수료인증 항목 저장
        certificateMapper.insertCertificate(certificate);
    }

    // courseId를 courseName, academyName, sessionNumber로 찾기
    public Long findCourseIdByDetails(String courseName, String academyName, int sessionNumber) {
        return certificateMapper.findCourseIdByDetails(courseName, academyName, sessionNumber);
    }

    // 로그인된 사용자의 수료 항목 조회
    public List<CertificateDTO> getCertificatesByMemberId(Long memberId) {
        return certificateMapper.selectCertificatesByMemberId(memberId);
    }

    // 모든 수료 항목 조회 (관리자용)
    public List<CertificateDTO> getAllCertificates() {
        return certificateMapper.selectAllCertificates();
    }

    // 수료 상태 업데이트
    public void updateCertificateStatus(Long certificateId, String status) {
        certificateMapper.updateCertificateStatus(certificateId, status);
    }
}