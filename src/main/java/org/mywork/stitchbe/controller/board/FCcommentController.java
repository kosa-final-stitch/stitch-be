package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.board.FCcommentDto;
import org.mywork.stitchbe.service.board.FCcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//작성자 : 박주희

@RestController
@RequestMapping("/api/comments")
public class FCcommentController {
    @Autowired
    private FCcommentService commentService;

    // 특정 게시글에 대한 댓글 목록 조회
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<FCcommentDto>> getCommentsByBoardId(@PathVariable Long boardId) {
        List<FCcommentDto> comments = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 작성
    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody FCcommentDto commentDto) {
        commentService.createComment(commentDto);
        return ResponseEntity.ok("댓글이 성공적으로 작성되었습니다.");
    }

    // 댓글 수정
    @PutMapping("/update")
    public ResponseEntity<String> updateComment(@RequestBody FCcommentDto commentDto) {
        commentService.updateComment(commentDto);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }

}
