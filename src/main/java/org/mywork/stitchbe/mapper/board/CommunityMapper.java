package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.Board.CommunityDto;
import java.util.List;

@Mapper
public interface CommunityMapper {
    void createPost(CommunityDto communityDto);
    CommunityDto getPostById(Long boardId);
    List<CommunityDto> getAllPosts();
    void updatePost(CommunityDto communityDto);
    void deletePost(Long boardId);
}
