package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.board.FCcommentDto;

import java.util.List;

//작성자 : 박주희

@Mapper
public interface FCcommentMapper {
    //특정 게시글에 대한 댓글 목록 조회
    List<FCcommentDto> getCommentsByBoardId(@Param("boardId") Long boardId);

    // 댓글 작성
    void createComment(FCcommentDto commentDto);

    // 댓글 수정
    void updateComment(FCcommentDto commentDto);

    // 댓글 삭제
    void deleteComment(@Param("commentId") Long commentId);
}
