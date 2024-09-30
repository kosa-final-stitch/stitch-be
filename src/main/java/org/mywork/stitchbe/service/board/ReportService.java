/*
 담당자:
 시작 일자: 2024.09.24
 설명 : 신고 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고목록 조회 기능 추가.
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

    public void createReport(ReportDto reportDto) {
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


    // 신고 목록 조회 (호영)
    public List<ReportDto> getReports() {
        return reportMapper.selectReports();
    }
}
