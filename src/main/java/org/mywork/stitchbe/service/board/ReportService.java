package org.mywork.stitchbe.service.board;

import org.mywork.stitchbe.dto.board.ReportDto;
import org.mywork.stitchbe.mapper.board.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    public void createReport(ReportDto reportDto) {
<<<<<<< HEAD
        // 유효성 검사: boardId와 commentId가 모두 null인 경우 예외 처리
        if (reportDto.getBoardId() == null && reportDto.getCommentId() == null) {
            throw new IllegalArgumentException("게시글 ID 또는 댓글 ID가 필요합니다.");
        }

        // 게시글 신고인지 댓글 신고인지에 따라 분기 처리
        if (reportDto.getBoardId() != null) {
            // 게시글 신고 처리 로직 추가
            handleBoardReport(reportDto);
        } else if (reportDto.getCommentId() != null) {
            // 댓글 신고 처리 로직 추가
            handleCommentReport(reportDto);
        }
        // 신고 데이터 저장
        reportMapper.insertReport(reportDto);
    }
    // 게시글 신고 처리 로직
    private void handleBoardReport(ReportDto reportDto) {
        Long boardId = reportDto.getBoardId();
        // 게시글 신고 처리 로직 작성
        // 예: boardService.increaseReportCount(boardId);
        System.out.println("게시글 신고 처리 로직 실행: 게시글 ID - " + boardId);
    }

    // 댓글 신고 처리 로직
    private void handleCommentReport(ReportDto reportDto) {
        Long commentId = reportDto.getCommentId();
        // 댓글 신고 처리 로직 작성
        // 예: commentService.increaseReportCount(commentId);
        System.out.println("댓글 신고 처리 로직 실행: 댓글 ID - " + commentId);
    }
=======
        // 게시글인지 댓글인지에 따라 분기 처리
        if ("POST".equals(reportDto.getPostOrComment())) {
            // 게시글 신고 처리 로직이 필요한 경우 여기에 추가
            // 예: 게시글의 상태를 변경하거나, 신고 카운트를 증가시키는 로직 등
            System.out.println("게시글 신고 처리 로직 실행");
        } else if ("COMMENT".equals(reportDto.getPostOrComment())) {
            // 댓글 신고 처리 로직이 필요한 경우 여기에 추가
            // 예: 댓글의 상태를 변경하거나, 신고 카운트를 증가시키는 로직 등
            System.out.println("댓글 신고 처리 로직 실행");
        }

        // 신고 데이터 저장
        reportMapper.insertReport(reportDto);
    }
>>>>>>> 1933334939ac4b7a886e1e0104675b60e38c2132
}
