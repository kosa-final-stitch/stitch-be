/*
 담당자:김호영
 시작 일자: 2024.09.29
 설명 : 신고 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고목록 조회 기능 추가.
 2024.09.30 김호영 | 신고한 게시물, 댓글 내용 및 작성자 정보 API 구현.
 */

package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.board.ReportDto;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    // 신고 데이터 삽입
    void insertReport(ReportDto reportDto);

    // 신고 목록 조회 (호영)
    List<ReportDto> selectReports(); // 추가된 신고 목록 조회 메서드

    // 신고 세부 정보 조회
    ReportDto selectReportById(Long reportId);

    // 게시글 내용 조회
    Map<String, Object> getPostContent(Long boardId);

    // 댓글 내용 조회
    String getCommentContent(Long commentId);

    
    // 신고된 작성자
    String getPostWriter(Long boardId);

    
    // 신고된 댓글 작성자
    String getCommentWriter(Long commentId);
    
    
    // 신고된 게시글 제목
    String getPostTitle(Long boardId);
}
