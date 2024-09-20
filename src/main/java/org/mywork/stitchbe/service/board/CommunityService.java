package org.mywork.stitchbe.service.board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mywork.stitchbe.mapper.board.CommunityMapper;
import org.mywork.stitchbe.dto.board.CommunityDto;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CommunityService {
    private static final Logger logger = LoggerFactory.getLogger(CommunityService.class);


    @Autowired
    private CommunityMapper communityMapper;

//     게시글 생성
    public void createPost(CommunityDto communityDto) {
        if (communityDto != null) {
            communityMapper.createPost(communityDto);  // Optional 없이 바로 전달
        } else {
            throw new IllegalArgumentException("CommunityDto cannot be null");
        }
    }

    // 게시글 상세 조회
    public CommunityDto getPostById(Long boardId) {
        System.out.println("Fetching community board with boardId: " + boardId);
        CommunityDto community = communityMapper.getPostById(boardId);

        // 조회 결과 확인
        System.out.println("Fetched community: " + community);

        return community;
    }

//    // 게시글 목록 조회
//    public List<CommunityDto> getAllPosts() {
//        return communityMapper.getAllPosts();
//    }
    public List<CommunityDto> getAllPosts() {
        logger.info("Fetching all posts");
        List<CommunityDto> posts = null;
        try {
            posts = communityMapper.getAllPosts();
            logger.info("Fetched posts: {}", posts);
        } catch (Exception e) {
            logger.error("Error fetching all posts: {}", e.getMessage(), e);
        }
        return posts;
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
