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
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 신고 등록 API
    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody ReportDto reportDto) {

        System.out.println("Received Report Data: " + reportDto);

        // 유효성 검사: boardId와 commentId가 모두 null인 경우 예외 처리
        if (reportDto.getBoardId() == null && reportDto.getCommentId() == null) {
            return ResponseEntity.badRequest().body("게시글 ID 또는 댓글 ID가 필요합니다.");
        }

        try {
            reportService.createReport(reportDto);
            return ResponseEntity.ok("신고가 접수되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("신고 처리 중 오류가 발생했습니다.");
        }
    }
}
