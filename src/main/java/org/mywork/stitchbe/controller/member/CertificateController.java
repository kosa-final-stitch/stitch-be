/*
담당자: 김호영
시작 일자: 2024.09.29
설명 : 수료과목 인증 관련 API 구현
---------------------
2024.09.29 김호영 | 수료 과목 인증 조회 등록 수정 API 생성.
2024.10.03 김호영 | 교육명, 학원명, 회차 정보로 course_id 조회 구현
*/

package org.mywork.stitchbe.controller.member;

import org.mywork.stitchbe.dto.CertificateDTO;
import org.mywork.stitchbe.service.CertificateService;
import org.mywork.stitchbe.service.MemberService;
import org.mywork.stitchbe.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private MemberService memberService;  // MemberService 추가

    @Autowired
    private S3Service s3Service; // S3Service 추가

    // 수료인증 등록
    @PostMapping("/register")
    public ResponseEntity<String> registerCertificate(
            @RequestParam("courseName") String courseName,
            @RequestParam("academyName") String academyName,
            @RequestParam("sessionNumber") int sessionNumber,
            @RequestParam("completionDate") String completionDate,
            @RequestParam("status") String status,
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ) {
        try {
            // 로그인된 사용자의 이메일을 통해 memberId 가져오기
            String email = authentication.getName();
            Long memberId = memberService.findMemberIdByEmail(email);

            // completionDate를 String에서 Date로 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedCompletionDate = dateFormat.parse(completionDate);

            Long courseId = certificateService.findCourseIdByDetails(courseName, academyName, sessionNumber);
            if (courseId == null) {
                return new ResponseEntity<>("해당 조건에 맞는 과정이 없습니다.", HttpStatus.BAD_REQUEST);
            }

            // S3에 파일 업로드 후 URL 반환
            String fileUrl = s3Service.uploadFile(file);

            // 서비스에 수료인증 등록 (파일 URL을 함께 저장)
            certificateService.registerCertificate(courseName, academyName, parsedCompletionDate, status, file, memberId, courseId);

            return new ResponseEntity<>(fileUrl, HttpStatus.CREATED);  // 파일 URL 반환
        } catch (Exception e) {
            return new ResponseEntity<>("수료인증 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인된 사용자 수료 인증 항목 조회
    @GetMapping("/list")
    public ResponseEntity<List<CertificateDTO>> getCertificatesByMemberId(Authentication authentication) {
        try {
            // 인증된 사용자 이메일 가져오기
            String email = authentication.getName();

            // 이메일을 이용해 memberId 조회 (MemberService에서 구현)
            Long memberId = memberService.findMemberIdByEmail(email);

            // 조회된 memberId로 수료 인증 항목 조회
            List<CertificateDTO> certificates = certificateService.getCertificatesByMemberId(memberId);

            return new ResponseEntity<>(certificates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 관리자가 모든 수료 항목을 조회하는 API
    @GetMapping("/all")
    public ResponseEntity<List<CertificateDTO>> getAllCertificates() {
        List<CertificateDTO> certificates = certificateService.getAllCertificates();
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    // 수료 상태 변경 API (관리자만)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateCertificateStatus(
            @RequestBody Map<String, Object> requestBody) {
        try {
            Long certificateId = Long.parseLong(requestBody.get("certificateId").toString());
            String status = requestBody.get("status").toString();

            // 수료 상태 변경 처리
            certificateService.updateCertificateStatus(certificateId, status);
            return new ResponseEntity<>("수료 상태가 성공적으로 변경되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("수료 상태 변경에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 특정 파일에 대한 서명된 URL을 반환하는 API
    @GetMapping("/signed-url")
    public ResponseEntity<String> getSignedUrl(@RequestParam String filename) {
        try {
            String signedUrl = s3Service.generatePresignedUrl(filename);
            return new ResponseEntity<>(signedUrl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Signed URL 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}