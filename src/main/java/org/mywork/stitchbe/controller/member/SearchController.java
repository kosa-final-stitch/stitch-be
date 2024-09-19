/*
 담당자: 박요한
 시작 일자: 2024.09.19
 설명 : 검색 용 Controller.
 _____________________
 2024.9.19 박요한 | 생성.
*/

package org.mywork.stitchbe.controller.member;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.SearchDTO;
import org.mywork.stitchbe.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 검색 API 엔드포인트
    @GetMapping("/api/search")
    public ResponseEntity<List<SearchDTO>> searchAcademyOrCourse(@RequestParam String query) {
        List<SearchDTO> results = searchService.searchAcademyOrCourse(query);
        return ResponseEntity.ok(results);
    }
}
