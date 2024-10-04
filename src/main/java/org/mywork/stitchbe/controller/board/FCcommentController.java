package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.domain.Member;
import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.dto.board.FCcommentDto;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.mywork.stitchbe.service.board.FCcommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//작성자 : 박주희

@RestController
@RequestMapping("/api")
public class FCcommentController {

    private static final Logger log = LoggerFactory.getLogger(FCcommentController.class);

    @Autowired
    private FCcommentService commentService;

    @Autowired
    private MemberMapper memberMapper;

    // 특정 게시글에 대한 댓글 목록 조회
    @GetMapping("/comments/board/{boardId}")
    public ResponseEntity<List<FCcommentDto>> getCommentsByBoardId(@PathVariable Long boardId) {
        List<FCcommentDto> comments = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 작성
    @PostMapping("/member/comments/create")
    public ResponseEntity<String> createComment(@RequestBody FCcommentDto commentDto) {
        log.debug("Received request to create comment: {}", commentDto);
        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("Authentication: {}", authentication);

        if (authentication == null || authentication.getName() == null) {
            // 인증되지 않은 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // 로그인된 사용자의 이메일을 가져옴
        String email = authentication.getName();

        // 이메일로 memberId 조회 (MemberMapper 사용)
        Long memberId = memberMapper.findMemberIdByEmail(email);
        if (memberId == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // 조회된 memberId와 nickname을 FCcommentDto에 설정
        commentDto.setMemberId(memberId);
        commentDto.setNickname(memberMapper.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")).getNickname());

        log.debug("FCcommentDto with memberId: {}", commentDto.getMemberId());
        log.debug("FCcommentDto with nickname: {}", commentDto.getNickname());

        // 댓글 작성 처리
        commentService.createComment(commentDto);

        return ResponseEntity.ok("댓글이 성공적으로 작성되었습니다.");
    }

    // 댓글 수정
    @PutMapping("/comments/update/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable long commentId, @RequestBody FCcommentDto commentDto) {
        commentDto.setCommentId(commentId);
        commentService.updateComment(commentDto);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    // 댓글 삭제
    @DeleteMapping("/comments/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }

}
