/*
담당자: 박요한
시작 일자: 2024.09.17
설명 : 홈페이지 용 course 와 review join dto
---------------------
2024.09.17 박요한 | dto 생성.
*/

package org.mywork.stitchbe.dto.home;

import lombok.Data;

@Data
public class CourseReviewDTO {
    private Long courseId;           // 교육 과정 ID
    private String courseName;       // 교육 과정 이름
    private Integer sessionNumber;   // 회차
    private String startDate;        // 시작일
    private String endDate;          // 종료일
    private Long academyId;          // 학원 ID
    private String academyName;      // 학원 이름

    // 평균 평점
    private Double averageRating;

    // 각 항목별 평균 평점
    private Double educationRating;
    private Double instructorRating;
    private Double facilityRating;
    private Double atmosphereRating;
    private Double managementRating;
    private Double laterRating;
}
