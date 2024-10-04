package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.board.InfoCommentDto;

import java.util.List;

@Mapper
public interface InfoCommentMapper {
    //특정 게시글에 대한 댓글 목록 조회
    List<InfoCommentDto> getCommentsByBoardId(@Param("boardId") Long boardId);

    // 댓글 작성
    void createComment(InfoCommentDto infoCommentDto);

    // 댓글 수정
    void updateComment(InfoCommentDto infoCommentDto);

    // 댓글 삭제
    void deleteComment(@Param("commentId") Long commentId);
}
