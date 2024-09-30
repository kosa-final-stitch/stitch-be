/*
담당자: 김호영
시작 일자: 2024.09.29
설명 : 수료과목 인증 Service
---------------------
2024.09.29 김호영 | 수료 과목 인증 조회 등록 수정 Service 생성.
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

    // 수료인증 등록
    public void registerCertificate(String courseName, String academyName, Date completionDate,
                                    String status, MultipartFile file, Long memberId, Long courseId) {
        CertificateDTO certificate = new CertificateDTO();
        certificate.setCourseName(courseName);
        certificate.setAcademyName(academyName);
        certificate.setCompletionDate(completionDate); // Date 형식으로 전달받아 저장
        certificate.setStatus(status);
        certificate.setFilename(file.getOriginalFilename());
        certificate.setMemberId(memberId); // 추가: 로그인된 회원의 ID 설정
        certificate.setCourseId(courseId); // 추가: 과정 ID 설정

        certificateMapper.insertCertificate(certificate);
    }

    // 로그인된 사용자의 수료 항목 조회
    public List<CertificateDTO> getCertificatesByMemberId(Long memberId) {
        return certificateMapper.selectCertificatesByMemberId(memberId);
    }

    // 모든 수료 항목 조회 (관리자용)
    public List<CertificateDTO> getAllCertificates() {
        return certificateMapper.selectAllCertificates();
    }

    public void updateCertificateStatus(Long certificateId, String status) {
        certificateMapper.updateCertificateStatus(certificateId, status);
    }
}