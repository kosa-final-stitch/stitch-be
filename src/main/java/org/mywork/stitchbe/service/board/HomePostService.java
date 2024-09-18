/*
 담당자: 박요한
 시작 일자: 2024.09.18
 설명 : 홈페이지 용 게시글 Service.
 _____________________
 2024.9.18 박요한 | 생성.
*/

package org.mywork.stitchbe.service.board;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.home.HomePostDTO;
import org.mywork.stitchbe.mapper.board.HomePostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 HomePostMapper의 의존성을 자동으로 주입
public class HomePostService {

    private final HomePostMapper homePostMapper;

    // 정보 공유 게시판 인기 게시글 가져오기
    public List<HomePostDTO> getTopInfoSharePost() {
        return homePostMapper.getTopInfoSharePost();
    }

    // 자유 게시판 인기 게시글 가져오기
    public List<HomePostDTO> getTopFreeCommunityPost() {
        return homePostMapper.getTopFreeCommunityPost();
    }

    // Q&A 게시판 인기 게시글 가져오기
    public List<HomePostDTO> getTopQnAPost() {
        return homePostMapper.getTopQnAPost();
    }
}
