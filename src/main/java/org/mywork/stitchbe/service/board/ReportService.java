/*
 담당자:김호영
 시작 일자: 2024.09.29
 설명 : 신고 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고목록 조회 기능 추가.
 2024.09.30 김호영 | 신고한 게시물, 댓글 내용 및 작성자 정보 API 구현.
 */

package org.mywork.stitchbe.service.board;

import org.mywork.stitchbe.dto.board.ReportDto;
import org.mywork.stitchbe.mapper.board.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    // 신고 등록 로직
    public void createReport(ReportDto reportDto) {
        // 게시글 신고 처리 로직
        if ("POST".equals(reportDto.getPostOrComment())) {
            System.out.println("게시글 신고 처리 로직 실행");
        } else if ("COMMENT".equals(reportDto.getPostOrComment())) {
            System.out.println("댓글 신고 처리 로직 실행");
        }

        // 신고 데이터 저장
        reportMapper.insertReport(reportDto);
    }

    // 신고 목록 조회
    public List<ReportDto> getReports() {
        return reportMapper.selectReports();
    }

    // 신고된 게시물/댓글의 작성자 닉네임 가져오기
    public String getReportWriter(Long reportId) {
        ReportDto report = reportMapper.selectReportById(reportId);
        if ("POST".equals(report.getPostOrComment())) {
            return reportMapper.getPostWriter(report.getBoardId());
        } else if ("COMMENT".equals(report.getPostOrComment())) {
            return reportMapper.getCommentWriter(report.getCommentId());
        }
        return null;
    }

    // 신고된 게시물/댓글 내용 가져오기
    public String getReportContent(Long reportId) {
        ReportDto report = reportMapper.selectReportById(reportId);
        if ("POST".equals(report.getPostOrComment())) {
            return reportMapper.getPostContent(report.getBoardId());
        } else if ("COMMENT".equals(report.getPostOrComment())) {
            return reportMapper.getCommentContent(report.getCommentId());
        }
        return null;
    }
}
