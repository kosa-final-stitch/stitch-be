/*
담당자: 박요한
시작 일자: 2024.09.15
설명 : 홈페이지 용 academy 와 review join dto
---------------------
2024.09.15 박요한 | dto 생성.
*/

package org.mywork.stitchbe.dto.home;

import lombok.Data;

@Data
public class AcademyReviewDTO {
    private Long academyId;
    private String academyName;
    private String address;
    private String phone;
    private Double averageRating;  // 리뷰의 평균 평점
    // 6개의 세부 항목별 평점
    private Double educationRating;
    private Double instructorRating;
    private Double facilityRating;
    private Double atmosphereRating;
    private Double managementRating;
    private Double laterRating;
}
