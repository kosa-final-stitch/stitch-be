/*
 2024.09.17. 박요한 | getTopLikedReviews 추가
 2024.10.01. 김호영 | 모든 리뷰데이터 조회, 삭제 API 추가
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
	
	// 특정 강의에 대한 리뷰를 가져오는 메서드
	public List<ReviewDTO> getReviewsByCourseId(Long courseId) {
		 List<ReviewDTO> reviews = reviewMapper.getReviewsByCourseId(courseId);
		 System.out.println(" 특정 강의에 리뷰리스트 : "+reviews);
		 for (ReviewDTO review : reviews) {
			    System.out.println("리뷰 작성자: " 
		 +" 리뷰 getAtmosphere : " + review.getAtmosphere()
		 +" 리뷰 getAtmosphereRating : "+ review.getAtmosphereRating()
		 +" 리뷰 getManagementRating : "	 + review.getManagementRating()
		 +" 리뷰 getCourseId : "	 + review.getCourseId()
		 +" 리뷰 getMemberId : "	 + review.getMemberId()
		 +" 리뷰 getReviewId : " + review.getReviewId()
		 +" 리뷰 getRegDate : "	 + review.getRegDate());
			}
		return reviews;
	}

	
	// 로그인한 사용자의 리뷰를 가져오는 메서드
	public List<ReviewDTO> getReviewsByUserId(Long memberId) {
	    return reviewMapper.getReviewsByUserId(memberId);
	}



	// 홈: 좋아요 수가 많은 상위 리뷰를 가져오는 서비스 메서드
	public List<ReviewLikesDTO> getTopLikedReviews() {
		return reviewMapper.getTopLikedReviews();  // ReviewMapper의 쿼리 호출
	}


	// 모든 리뷰 가져오는 서비스 메서드
	public List<ReviewDTO> getAllReviews() {
		return reviewMapper.getAllReviews();
	}
	// 리뷰 삭제 서비스 메서드
	public void deleteReview(Long reviewId) {
        reviewMapper.deleteReview(reviewId);
    }
}
