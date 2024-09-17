/*
 2024.9.17. 박요한 | getTopLikedReviews 추가
*/

package org.mywork.stitchbe.controller.member;

import java.util.List;

import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.ReviewLikesDTO;
import org.mywork.stitchbe.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RESTful API를 위한 컨트롤러 클래스
@RequestMapping("/api/reviews") // API의 기본 경로를 설정
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	// 리뷰 데이터를 저장하는 엔드포인트
	@PostMapping
	public String saveReviews(@RequestBody List<ReviewDTO> reviews) {
		reviewService.saveReviews(reviews);
		return "리뷰가 성공적으로 저장되었습니다.";
	}

	// 모든 리뷰를 가져오는 엔드포인트 (선택적)
	@GetMapping
	public List<ReviewDTO> getAllReviews() {
		return reviewService.getAllReviews();
	}

	// 특정 강의에 대한 리뷰를 가져오는 엔드포인트 (선택적)
	@GetMapping("/course/{courseId}")
	public List<ReviewDTO> getReviewsByCourseId(@PathVariable Long courseId) {
		return reviewService.getReviewsByCourseId(courseId);
	}

	// 홈: 좋아요 수가 많은 상위 리뷰를 가져오는 API 엔드포인트
	@GetMapping("/top")
	public ResponseEntity<List<ReviewLikesDTO>> getTopLikedReviews() {
		List<ReviewLikesDTO> topLikedReviews = reviewService.getTopLikedReviews();
		return ResponseEntity.ok(topLikedReviews);
	}
}
