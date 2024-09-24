package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.board.CommunityDto;
import java.util.List;


@Mapper
public interface CommunityMapper {
    List<CommunityDto> getAllPosts();
    void createPost(CommunityDto post);
    CommunityDto getPostById(Long boardId);
    void updatePost(CommunityDto communityDto);
    void deletePost(Long boardId);
    void incrementViewCount(@Param("boardId") Long boardId);
    // 특정 사용자의 게시글 목록 조회(유은)
	List<CommunityDto> getPostsByUserId(Long userId);
}
