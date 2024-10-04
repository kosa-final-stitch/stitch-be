/*
 2024.9.17. 박요한 | getTopLikedReviews 추가
*/

package org.mywork.stitchbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.ReviewLikesDTO;

@Mapper
public interface ReviewMapper {
	// 리뷰 데이터를 삽입하는 메서드
	public void insertReview(ReviewDTO review);

	// 모든 리뷰를 가져오는 메서드 (선택적으로 추가 가능)
	List<ReviewDTO> getAllReviews();

	// 특정 강의에 대한 리뷰를 가져오는 메서드 (선택적으로 추가 가능)
	List<ReviewDTO> getReviewsByCourseId(Long courseId);

	// 홈: 상위 좋아요 리뷰를 가져오는 메서드
	List<ReviewLikesDTO> getTopLikedReviews();

	// 로그인 한사용자의 대한 리뷰를 가져오는 메서드(유은)
	public List<ReviewDTO> getReviewsByUserId(Long memberId);

	// 리뷰 삭제 메서드
	void deleteReview(Long reviewId);

	  // 특정 멤버가 작성한 특정 코스의 리뷰 상세 정보를 가져오는 API(1003)
	 ReviewDTO findReviewByMemberAndCourse(@Param("memberEmail") String memberEmail,
             @Param("academyId") Long academyId,
             @Param("courseId") Long courseId,
             @Param("reviewId") Long reviewId);
	 
	 // 특정 리뷰 조회 (1003) - 코스디테일 
	public ReviewDTO findReviewByAcademyCourseAndReview(Long academyId, Long courseId, Long reviewId);
}
