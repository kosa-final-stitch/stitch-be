/*
 담당자:
 시작 일자: 2024.09.24
 설명 : 신고 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고목록 조회 기능 추가.
 */

package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.board.ReportDto;

import java.util.List;

@Mapper
public interface ReportMapper {
    // 신고 데이터 삽입
    void insertReport(ReportDto reportDto);

    // 신고 목록 조회 (호영)
    List<ReportDto> selectReports(); // 추가된 신고 목록 조회 메서드

//
//    // 특정 신고 데이터 조회 (필요 시)
//    ReportDto selectReportById(Long reportId);
//
//    // 신고 데이터 업데이트 (필요 시)
//    void updateReport(ReportDto reportDto);
//
//    // 신고 데이터 삭제 (필요 시)
//    void deleteReport(Long reportId);
}
