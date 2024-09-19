/*
 담당자: 박요한
 시작 일자: 2024.09.19
 설명 : 검색 용 Service.
 _____________________
 2024.9.19 박요한 | 생성.
*/

package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.SearchDTO;
import org.mywork.stitchbe.mapper.SearchMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 의존성 자동 주입
public class SearchService {

    private final SearchMapper searchMapper;

    // 검색 키워드를 통해 학원 또는 과정 검색
    public List<SearchDTO> searchAcademyOrCourse(String keyword) {
        return searchMapper.searchAcademyOrCourse(keyword);
    }
}
