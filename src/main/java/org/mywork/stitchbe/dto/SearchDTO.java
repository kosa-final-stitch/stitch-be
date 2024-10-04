/*
담당자: 박요한
시작 일자: 2024.09.19
설명 : 검색창 결과 용 dto.
---------------------
2024.09.19 박요한 | dto 생성.
*/

package org.mywork.stitchbe.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private String type;       // 검색 결과 유형 (academy 또는 course)
    private Long academyId;    // 학원 ID
    private Long courseId;     // 과정 ID (학원 검색 결과인 경우 0)
    private String name;       // 학원명 또는 과정명
}
