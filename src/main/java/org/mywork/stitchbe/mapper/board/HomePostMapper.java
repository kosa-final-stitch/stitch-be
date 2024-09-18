/*
 담당자: 박요한
 시작 일자: 2024.09.18
 설명 : 홈페이지 용 게시글 mapper.
 _____________________
 2024.9.18 박요한 | 생성.
*/

package org.mywork.stitchbe.mapper.board;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.home.HomePostDTO;
import java.util.List;

@Mapper
public interface HomePostMapper {
    // 정보 공유 게시판에서 조회수가 높은 게시글을 가져오는 메서드
    List<HomePostDTO> getTopInfoSharePost();

    // 자유 게시판에서 조회수가 높은 게시글을 가져오는 메서드
    List<HomePostDTO> getTopFreeCommunityPost();

    // Q&A 게시판에서 조회수가 높은 게시글을 가져오는 메서드
    List<HomePostDTO> getTopQnAPost();
}