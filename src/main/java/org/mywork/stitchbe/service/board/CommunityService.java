package org.mywork.stitchbe.service.board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mywork.stitchbe.mapper.board.CommunityMapper;
import org.mywork.stitchbe.dto.Board.CommunityDto;

import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    // 게시글 생성
    public void createPost(CommunityDto communityDto) {
        communityMapper.createPost(communityDto);
    }

    // 게시글 상세 조회
    public CommunityDto getPostById(Long boardId) {
        return communityMapper.getPostById(boardId);
    }

    // 게시글 목록 조회
    public List<CommunityDto> getAllPosts() {
        return communityMapper.getAllPosts();
    }

    // 게시글 수정
    public void updatePost(CommunityDto communityDto) {
        communityMapper.updatePost(communityDto);
    }

    // 게시글 삭제
    public void deletePost(Long boardId) {
        communityMapper.deletePost(boardId);
    }
}
