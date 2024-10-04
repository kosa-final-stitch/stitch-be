package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.dto.board.InfoDto;

import java.util.List;

@Mapper
public interface InfoMapper {
    List<InfoDto> getAllPosts();
    void createPost(InfoDto post);
    InfoDto getPostById(Long boardId);
    void updatePost(InfoDto infoDto);
    void deletePost(Long boardId);
    void incrementViewCount(@Param("boardId") Long boardId);
}
