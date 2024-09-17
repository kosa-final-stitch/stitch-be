/*
담당자: 박요한
시작 일자: 2024.09.17
설명 : 홈페이지 용 review와 likes join dto
---------------------
2024.09.17 박요한 | dto 생성.
*/

package org.mywork.stitchbe.dto.home;

import lombok.Data;

@Data
public class ReviewLikesDTO {
    private Long reviewId;           // 리뷰 ID
    private Long courseId;           // 강좌 ID
    private Long memberId;           // 작성자(회원) ID
    private String educationReview;  // 교육에 대한 리뷰
    private String instructorReview; // 강사에 대한 리뷰
    private String facilityReview;   // 시설에 대한 리뷰
    private String atmosphereReview; // 분위기에 대한 리뷰
    private String managementReview; // 행정 처리에 대한 리뷰
    private String laterReview;      // 사후 관리에 대한 리뷰

    // 전체 평균 별점
    private Double averageRating;

    // 좋아요 수
    private Integer likeCount;
}
