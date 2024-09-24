package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.board.ReportDto;

@Mapper
public interface ReportMapper {
    // 신고 데이터 삽입
    void insertReport(ReportDto reportDto);
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
