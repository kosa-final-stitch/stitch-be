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

    // 게시글 생성
    public void createPost(CommunityDto communityDto) {
        communityMapper.createPost(Optional.ofNullable(communityDto));
    }

    // 게시글 상세 조회
    public CommunityDto getPostById(Long boardId) {
        return communityMapper.getPostById(boardId);
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
