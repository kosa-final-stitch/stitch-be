/*
 2024.9.17. 박요한 | getTopLikedReviews 추가
*/

package org.mywork.stitchbe.service;

import java.util.List;

import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.ReviewLikesDTO;
import org.mywork.stitchbe.mapper.ReviewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

	private final ReviewMapper reviewMapper;

	public ReviewService(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}

	// 리뷰 데이터를 저장하는 메서드
	public void saveReviews(List<ReviewDTO> reviews) {
		for (ReviewDTO review : reviews) {
			reviewMapper.insertReview(review);
		}
		System.out.println("리뷰서비스 호출 및 : "+reviews);
	}

	// 모든 리뷰 데이터를 가져오는 메서드 (선택적)
	public List<ReviewDTO> getAllReviews() {
		return reviewMapper.getAllReviews();
	}

	// 특정 강의에 대한 리뷰를 가져오는 메서드 (선택적)
	public List<ReviewDTO> getReviewsByCourseId(Long courseId) {
		return reviewMapper.getReviewsByCourseId(courseId);
	}

	// 홈: 좋아요 수가 많은 상위 리뷰를 가져오는 서비스 메서드
	public List<ReviewLikesDTO> getTopLikedReviews() {
		return reviewMapper.getTopLikedReviews();  // ReviewMapper의 쿼리 호출
	}
}
