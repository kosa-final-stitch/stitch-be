package org.mywork.stitchbe.service.board;

import org.mywork.stitchbe.dto.board.InfoCommentDto;
import org.mywork.stitchbe.mapper.board.InfoCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoCommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommunityService.class);

    @Autowired
    private InfoCommentMapper infoCommentMapper;

    // 특정 게시글에 대한 댓글 목록 조회
    public List<InfoCommentDto> getCommentsByBoardId(Long boardId) {
        return infoCommentMapper.getCommentsByBoardId(boardId);
    }

    // 댓글 작성
    public void createComment(InfoCommentDto infoCommentDto) {
        if (infoCommentDto == null) {
            throw new IllegalArgumentException("CommentDto cannot be null");
        }

        if (infoCommentDto.getContent() == null || infoCommentDto.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be null or empty");
        }

        if (infoCommentDto.getMemberId() == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }

        if (infoCommentDto.getBoardId() == null) {
            throw new IllegalArgumentException("Board ID cannot be null");
        }

        // 댓글 작성 처리
        infoCommentMapper.createComment(infoCommentDto);    }

    // 댓글 수정
    public void updateComment(InfoCommentDto infoCommentDto) {
        infoCommentMapper.updateComment(infoCommentDto);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        infoCommentMapper.deleteComment(commentId);
    }
}
