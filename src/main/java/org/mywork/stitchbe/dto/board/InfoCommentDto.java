package org.mywork.stitchbe.dto.board;

import lombok.Data;

import java.util.Date;

@Data
public class InfoCommentDto {
    private Long commentId;        // 댓글 ID
    private Long boardId;          // 게시글 ID
    private Long memberId;         // 작성자 ID
    private String nickname;       // 작성자 닉네임
    private String content;        // 댓글 내용
    private Date regdate;          // 작성일
    private int likeCount;         // 좋아요 수
    private Date editdate;         // 수정일
    private Long parentCommentId;  // 부모 댓글 ID (대댓글일 경우)
}
