/*
 2024.9.17. 박요한 | getTopLikedReviews 추가
*/

package org.mywork.stitchbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
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
}
