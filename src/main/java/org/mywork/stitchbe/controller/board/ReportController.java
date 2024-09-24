package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.board.ReportDto;
import org.mywork.stitchbe.service.board.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
