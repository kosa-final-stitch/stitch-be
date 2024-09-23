package org.mywork.stitchbe.service.board;

import org.mywork.stitchbe.dto.board.InfoDto;
import org.mywork.stitchbe.mapper.board.InfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    private static final Logger logger = LoggerFactory.getLogger(CommunityService.class);

    @Autowired
    private InfoMapper infoMapper;

    // 게시글 생성
    public void createPost(InfoDto infoDto) {
        if (infoDto != null) {
            infoMapper.createPost(infoDto);  // InfoMapper 인스턴스를 통해 메서드 호출
        } else {
            throw new IllegalArgumentException("InfoDto cannot be null");
        }
    }

    // 게시글 상세 조회
    public InfoDto getPostById(Long boardId) {
        logger.info("Fetching community board with boardId: {}", boardId);
        InfoDto info = infoMapper.getPostById(boardId); // InfoMapper 인스턴스를 통해 메서드 호출

        // 조회 결과 확인
        logger.info("Fetched community: {}", info);

        return info;
    }

    //게시글 목록 조회
    public List<InfoDto> getAllPosts() {
        logger.info("Fetching all posts");
        List<InfoDto> posts = null;
        try {
            posts = infoMapper.getAllPosts(); // InfoMapper 인스턴스를 통해 메서드 호출
            logger.info("Fetched posts: {}", posts);
        } catch (Exception e) {
            logger.error("Error fetching all posts: {}", e.getMessage(), e);
        }
        return posts;
    }

    // 게시글 수정
    public void updatePost(InfoDto infoDto) {
        infoMapper.updatePost(infoDto); // InfoMapper 인스턴스를 통해 메서드 호출
    }

    // 게시글 삭제
    public void deletePost(Long boardId) {
        infoMapper.deletePost(boardId); // InfoMapper 인스턴스를 통해 메서드 호출
    }

    // 조회수 증가
    public void incrementViewCount(Long boardId) {
        infoMapper.incrementViewCount(boardId); // InfoMapper 인스턴스를 통해 메서드 호출
    }
}
