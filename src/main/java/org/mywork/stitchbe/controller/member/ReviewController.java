/*
 2024.9.17. 박요한 | getTopLikedReviews 추가
*/

package org.mywork.stitchbe.controller.member;

import java.util.List;

import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.ReviewLikesDTO;
import org.mywork.stitchbe.service.MemberService;
import org.mywork.stitchbe.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController // RESTful API를 위한 컨트롤러 클래스
@RequestMapping("/api/member/reviews") // API의 기본 경로를 설정
public class ReviewController {

	private final ReviewService reviewService;
	private final MemberService memberService;

	public ReviewController(ReviewService reviewService, MemberService memberService) {
		this.reviewService = reviewService;
		this.memberService = memberService;
	}

	// 리뷰 데이터를 저장하는 엔드포인트
	// @PostMapping
//	public String saveReviews(@RequestBody List<ReviewDTO> reviews) {
//		reviewService.saveReviews(reviews);
//		return "리뷰가 성공적으로 저장되었습니다.";
//	}

	@PostMapping
	public ResponseEntity<String> saveReview(@RequestBody List<ReviewDTO> reviews) {
		System.out.println("리뷰컨트롤러 호출");
		try {
			reviewService.saveReviews(reviews);
			return ResponseEntity.ok("리뷰가 성공적으로 저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace(); // 오류 메시지를 자세히 출력
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 저장 중 오류가 발생했습니다.");
		}
	}

	// 특정 강의에 대한 리뷰를 가져오는 엔드포인트
	@GetMapping("/courses/{courseId}")
	public List<ReviewDTO> getReviewsByCourseId(@PathVariable Long courseId) {
		System.out.println("특정강의에 대한 리뷰를 가져오는 컨트롤러 호출");
		return reviewService.getReviewsByCourseId(courseId);
	}

	// 모든 리뷰를 가져오는 엔드포인트 (선택적)
//	@GetMapping
//	public List<ReviewDTO> getAllReviews() {
//		return reviewService.getAllReviews();
//	}

	// 로그인한 사용자의 모든 리뷰를 가져오는 엔드포인트
	@GetMapping("/myreviews")
	public ResponseEntity<List<ReviewDTO>> getUserReviews() {
		// 현재 인증된 사용자 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("현재 인증된 사용자 리뷰가져오기: " + authentication);

		if (authentication != null && authentication.isAuthenticated()) {
			String email = authentication.getName(); // 사용자의 이메일을 가져옴

			// 이메일로 사용자 정보 조회
			MemberDto member = memberService.getMemberInfoByEmail(email);
			if (member != null) {
				Long userId = member.getMemberId(); // 사용자 ID 가져옴
				System.out.println("my reviews에서 가져온 사용자 아이디 : "+userId);

				// 해당 사용자의 리뷰 목록을 조회
				List<ReviewDTO> reviews = reviewService.getReviewsByUserId(userId);

							return ResponseEntity.ok(reviews);
			} else {
				return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
			}
		} else {
			return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(null);
		}
	}

	// 홈: 좋아요 수가 많은 상위 리뷰를 가져오는 API 엔드포인트
	@GetMapping("/top")
	public ResponseEntity<List<ReviewLikesDTO>> getTopLikedReviews() {
		List<ReviewLikesDTO> topLikedReviews = reviewService.getTopLikedReviews();
		return ResponseEntity.ok(topLikedReviews);
	}
}
