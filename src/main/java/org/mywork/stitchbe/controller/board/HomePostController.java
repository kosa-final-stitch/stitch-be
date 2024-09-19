/*
 담당자: 박요한
 시작 일자: 2024.09.18
 설명 : 홈페이지 용 게시글 Controller.
 _____________________
 2024.9.18 박요한 | 생성.
*/

package org.mywork.stitchbe.controller.board;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.home.HomePostDTO;
import org.mywork.stitchbe.service.board.HomePostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home/posts")
@RequiredArgsConstructor // Lombok 의존성 자동 주입
public class HomePostController {

    private final HomePostService homePostService;

    // 정보 공유 게시판 인기 게시글 가져오기
    @GetMapping("/info-share/top")
    public ResponseEntity<List<HomePostDTO>> getTopInfoSharePost() {
        List<HomePostDTO> posts = homePostService.getTopInfoSharePost();
        return ResponseEntity.ok(posts);
    }

    // 자유 게시판 인기 게시글 가져오기
    @GetMapping("/free-community/top")
    public ResponseEntity<List<HomePostDTO>> getTopFreeCommunityPost() {
        List<HomePostDTO> posts = homePostService.getTopFreeCommunityPost();
        return ResponseEntity.ok(posts);
    }

    // Q&A 게시판 인기 게시글 가져오기
    @GetMapping("/qna/top")
    public ResponseEntity<List<HomePostDTO>> getTopQnAPost() {
        List<HomePostDTO> posts = homePostService.getTopQnAPost();
        return ResponseEntity.ok(posts);
    }
}
