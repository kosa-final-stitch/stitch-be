package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.board.QnaDto;

import java.util.List;

@Mapper
public interface QnaMapper {
    List<QnaDto> getAllPosts();
    void createPost(QnaMapper post);
    QnaDto getPostById(Long boardId);
    void updatePost(QnaDto qnaDto);
    void deletePost(Long boardId);
    void incrementViewCount(@Param("boardId") Long boardId);
}
