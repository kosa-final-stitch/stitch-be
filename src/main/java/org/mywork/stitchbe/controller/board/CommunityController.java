package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.service.board.CommunityService;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class CommunityController {
    private static final Logger log = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    private CommunityService communityService;

    @Autowired
    private MemberMapper memberMapper;

    //     게시글 목록 조회
    @GetMapping("/board/community/all")
    public ResponseEntity<List<CommunityDto>> getAllPosts() {
        List<CommunityDto> posts = communityService.getAllPosts();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build(); // 게시글이 없을 때 HTTP 204 응답
        }
        return ResponseEntity.ok(posts);
    }


    // 게시글 생성
    @PostMapping("/member/board/community/create")
    public ResponseEntity<String> createPost(@RequestBody CommunityDto communityDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("Authentication: {}", authentication);

        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // 로그인된 사용자의 이메일을 가져옴
        String email = authentication.getName();

        // 이메일로 memberId 조회 (MemberMapper 사용)
        Long memberId = memberMapper.findMemberIdByEmail(email);
        if (memberId == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // 조회된 memberId와 nickname을 CommunityDto에 설정
        communityDto.setMemberId(memberId);
        // 필요한 경우 nickname도 설정
        communityDto.setNickname(memberMapper.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found")).getNickname());

        log.debug("CommunityDto with memberId: {}", communityDto.getMemberId());
        log.debug("CommunityDto with nickName: {}", communityDto.getNickname());

        // 게시글 작성 처리
        communityService.createPost(communityDto);

        return ResponseEntity.ok("Post created successfully");
    }

    // 게시글 상세 조회
    @GetMapping("/board/post/{boardId}")
    public ResponseEntity<CommunityDto> getPostById(@PathVariable Long boardId) {
        System.out.println("Controller method called");
        // boardId 값 확인을 위한 로그 추가
        log.debug("Received boardId: {}", boardId);
        // 유효성 검사 (필요 시)
        if (boardId == null || boardId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        // 게시글 조회
        CommunityDto post = communityService.getPostById(boardId);
        // 게시글 데이터 확인을 위한 로그 추가
        log.debug("Post Data: {}", post);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @GetMapping("/all")
//    public String getAllPosts() {
////        List<CommunityDto> posts = communityService.getAllPosts();
//        // 인증된 사용자인지 확인
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            // 사용자 이름과 권한 확인
//            System.out.println("현재 인증된 사용자: " + authentication.getName());
//            System.out.println("사용자의 권한: " + authentication.getAuthorities());
//        }
//        return "박주희 천재";
//    }

    // 게시글 수정
    @PutMapping("board/post/update/{boardId}")
    public ResponseEntity<String> updatePost(@PathVariable Long boardId, @RequestBody CommunityDto communityDto) {
        communityDto.setBoardId(boardId);
        communityService.updatePost(communityDto);
        return ResponseEntity.ok("Post updated successfully");
    }

    // 게시글 삭제
    @DeleteMapping("board/post/delete/{boardId}")
    public ResponseEntity<String> deletePost(@PathVariable Long boardId) {
        communityService.deletePost(boardId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    //조회수
    @PostMapping("/board/post/increment-views/{boardId}")
    public ResponseEntity<?> incrementViews(@PathVariable Long boardId) {
        try {
            communityService.incrementViewCount(boardId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to increment view count");
        }
    }
    
 // 로그인한 사용자의  게시글 목록 조회(유은)
    @GetMapping("/board/community/{memberId}")
    public ResponseEntity<List<CommunityDto>> getPostsByUserId(@PathVariable Long memberId) {
    	System.out.println("(마페)게시글 목록조회 컨트롤러");
        List<CommunityDto> posts = communityService.getPostsByUserId(memberId);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }
   

    
}
