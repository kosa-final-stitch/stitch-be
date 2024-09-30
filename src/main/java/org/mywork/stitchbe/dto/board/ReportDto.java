package org.mywork.stitchbe.dto.board;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDto {
    private Long reportId; // 신고 ID
    private Long memberId; // 신고한 회원 ID
<<<<<<< HEAD
    private Long boardId; // 게시글 ID
    private Long commentId; // 댓글 ID (null일 수 있음)
    private String type; // 신고 유형
=======
    private String postOrComment; // 게시글 또는 댓글 구분
    private String boardTable; // 게시글이 속한 테이블 이름
    private Long boardId; // 게시글 ID
    private Long commentId; // 댓글 ID (null일 수 있음)
    private String type; // 신고 유형
    private String reason; // 신고 사유
>>>>>>> 1933334939ac4b7a886e1e0104675b60e38c2132
    private String content; // 신고 내용
    private Date regdate; // 신고 등록 날짜
}
