package org.mywork.stitchbe.dto.board;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDto {
    private Long reportId; // 신고 ID
    private Long memberId; // 신고한 회원 ID
    private Long boardId; // 게시글 ID
    private Long commentId; // 댓글 ID (null일 수 있음)
    private String type; // 신고 유형
    private String content; // 신고 내용
    private Date regdate; // 신고 등록 날짜
}
