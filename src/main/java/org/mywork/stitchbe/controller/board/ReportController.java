/*
 담당자:
 시작 일자: 2024.09.24
 설명 : 신고 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고목록 조회 기능 추가.
 */


package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.board.ReportDto;
import org.mywork.stitchbe.service.board.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
