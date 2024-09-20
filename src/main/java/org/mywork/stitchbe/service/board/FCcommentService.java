package org.mywork.stitchbe.service.board;

import org.mywork.stitchbe.dto.board.FCcommentDto;
import org.mywork.stitchbe.mapper.board.FCcommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//작성자 : 박주희

@Service
public class FCcommentService {
    @Autowired
    private FCcommentMapper commentMapper;

    // 특정 게시글에 대한 댓글 목록 조회
    public List<FCcommentDto> getCommentsByBoardId(Long boardId) {
        return commentMapper.getCommentsByBoardId(boardId);
    }

    // 댓글 작성
    public void createComment(FCcommentDto commentDto) {
        commentMapper.createComment(commentDto);
    }

    // 댓글 수정
    public void updateComment(FCcommentDto commentDto) {
        commentMapper.updateComment(commentDto);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }
}
