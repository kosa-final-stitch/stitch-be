/*
 담당자: 박요한
 시작 일자: 2024.09.19
 설명 : 검색 용 매퍼
 _____________________
 2024.9.19 박요한 | 생성.
*/
package org.mywork.stitchbe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.SearchDTO;

import java.util.List;

@Mapper
public interface SearchMapper {

    // 학원명 또는 과정명으로 검색하는 메서드
    List<SearchDTO> searchAcademyOrCourse(String keyword);
}
