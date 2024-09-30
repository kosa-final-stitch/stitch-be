/*
 담당자: 김호영
 시작 일자: 2024.09.29
 설명 : 신고 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고목록 조회 기능 추가.
 2024.09.30 김호영 | 신고한 게시물, 댓글 내용 및 작성자 정보 API 구현.
 */


package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.board.ReportDto;
import org.mywork.stitchbe.service.board.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/member/report")
public class ReportController {
    private final
    ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 신고 등록 API
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody ReportDto reportDto) {
        reportService.createReport(reportDto);
        return ResponseEntity.ok("신고가 접수되었습니다.");
    }

    // 신고 목록 조회 API (호영)
    @GetMapping
    public ResponseEntity<List<ReportDto>> getReports() {
        List<ReportDto> reports = reportService.getReports();
        return ResponseEntity.ok(reports);
    }

    // 신고된 게시물/댓글의 작성자 닉네임 조회
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{reportId}/writer")
    public ResponseEntity<String> getReportWriter(@PathVariable Long reportId) {
        try {
            String writerNickname = reportService.getReportWriter(reportId);
            return ResponseEntity.ok(writerNickname);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 신고된 게시물/댓글 내용 조회
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{reportId}/content")
    public ResponseEntity<Map<String, Object>> getReportContent(@PathVariable Long reportId) {
        try {
            Map<String, Object> contentMap = reportService.getReportContent(reportId);
            return ResponseEntity.ok(contentMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}