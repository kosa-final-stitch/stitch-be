/*
 담당자 : 김호영
 시작 일자: 2024.09.24
 설명 : 신고 DTO 구현 개발
 ---------------------
 2024.09.29 김호영 | 신고자 NickName 객체 추가.
 2024.09.30 김호영 | 신고한 게시물, 댓글 내용 및 작성자 정보 API 구현.
 */

package org.mywork.stitchbe.dto.board;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDto {
    private Long reportId; // 신고 ID
    private Long memberId; // 신고한 회원 ID
    private String nickname; // 신고자의 닉네임
    private String postOrComment; // 게시글 또는 댓글 구분
    private String boardTable; // 게시글이 속한 테이블 이름
    private Long boardId; // 게시글 ID
    private Long commentId; // 댓글 ID (null일 수 있음)
    private String type; // 신고 유형
    private String reason; // 신고 사유
    private String content; // 신고 내용
    private Date regdate; // 신고 등록 날짜
    private String postContent;  // 게시글 또는 댓글 내용
}
