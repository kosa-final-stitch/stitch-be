package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.board.CommunityDto;
import java.util.List;


@Mapper
public interface CommunityMapper {
    List<CommunityDto> getAllPosts();
    void createPost(CommunityDto post);
    CommunityDto getPostById(Long boardId);
    void updatePost(CommunityDto communityDto);
    void deletePost(Long boardId);
}
