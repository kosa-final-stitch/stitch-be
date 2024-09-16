package org.mywork.stitchbe.controller.board;

import org.mywork.stitchbe.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.service.board.CommunityService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

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

        if (authentication != null && authentication.getPrincipal() instanceof MemberDto) {
            MemberDto member = (MemberDto) authentication.getPrincipal();
            communityDto.setMemberId(member.getMemberId()); // 게시글에 memberId 추가
        }

        communityService.createPost(communityDto);
        return ResponseEntity.ok("Post created successfully");
    }


    // 게시글 상세 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<CommunityDto> getPostById(@PathVariable Long boardId) {
        CommunityDto post = communityService.getPostById(boardId);
        return ResponseEntity.ok(post);
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
    @PutMapping("/update/{boardId}")
    public ResponseEntity<String> updatePost(@PathVariable Long boardId, @RequestBody CommunityDto communityDto) {
        communityDto.setBoardId(boardId);
        communityService.updatePost(communityDto);
        return ResponseEntity.ok("Post updated successfully");
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<String> deletePost(@PathVariable Long boardId) {
        communityService.deletePost(boardId);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
