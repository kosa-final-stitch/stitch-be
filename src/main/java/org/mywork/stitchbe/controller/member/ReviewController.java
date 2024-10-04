/*
 2024.09.17. 박요한 | getTopLikedReviews 추가
 2024.10.01. 김호영 | 모든 리뷰데이터 조회, 삭제 API 추가
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
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

	// 특정 강의에 대한 리뷰 저장
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


	// 모든 리뷰를 가져오는 엔드포인트 (호영)
	@GetMapping("/all")
	public ResponseEntity<List<ReviewDTO>> getAllReviews() {
		try {
			List<ReviewDTO> reviews = reviewService.getAllReviews();
			return ResponseEntity.ok(reviews);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	 // 특정 리뷰 조회 (1003) - 코스디테일 
    @GetMapping("/{academyId}/course/{courseId}/review/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewDetail(
        @PathVariable Long academyId, 
        @PathVariable Long courseId, 
        @PathVariable Long reviewId) {

        ReviewDTO review = reviewService.getReviewDetail(academyId, courseId, reviewId);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	  // 특정 멤버가 작성한 특정 코스의 리뷰 상세 정보를 가져오는 API(1003) -마이페이지
    @GetMapping("/academy/{academyId}/course/{courseId}/review/{reviewId}")
    public ResponseEntity<String> getReviewDetailByMemberAndCourse (
            @PathVariable Long academyId, 
            @PathVariable Long courseId, 
            @PathVariable Long reviewId) {

        try {
            // 현재 세션에서 인증 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 인증 정보가 있는지 확인
            if (authentication != null && authentication.isAuthenticated()) {
                // 사용자 이름과 권한 확인
                System.out.println("현재 인증된 사용자: " + authentication.getName());
                System.out.println("사용자의 권한: " + authentication.getAuthorities());

                // 인증 객체가 UserDetails 타입인지 확인하고, 사용자 세부 정보 출력
                if (authentication.getPrincipal() instanceof User) {
                    User user = (User) authentication.getPrincipal();
                    System.out.println("사용자 이메일: " + user.getUsername());

                    // 리뷰를 가져오는 비즈니스 로직 추가
                    ReviewDTO review = reviewService.getReviewDetailByMemberAndCourse(user.getUsername(), academyId, courseId, reviewId);
                    if (review != null) {
                        return ResponseEntity.ok("리뷰 상세 정보: " + review.toString());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("리뷰를 찾을 수 없습니다.");
                    }
                }

                return ResponseEntity.ok("세션에 인증된 사용자: " + authentication.getName());
            } else {
                System.out.println("세션에 인증된 사용자가 없습니다.");
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("인증되지 않음");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

	// 로그인한 사용자의 모든 리뷰를 가져오는 엔드포인트
	@GetMapping("/myreviews/{memberId}")
	public ResponseEntity<List<ReviewDTO>> getUserReviews() {
		// 현재 인증된 사용자 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println("(리뷰컨)현재 인증된 사용자 리뷰 가져오기: " + authentication);

		if (authentication != null && authentication.isAuthenticated()) {
			String email = authentication.getName(); // 사용자의 이메일을 가져옴

			// 이메일로 사용자 정보 조회
			MemberDto member = memberService.getMemberInfoByEmail(email);
			if (member != null) {
				Long memberId = member.getMemberId(); // 사용자 ID 가져옴
				System.out.println("my reviews에서 가져온 사용자 아이디 : "+memberId);

				// 해당 사용자의 리뷰 목록을 조회
				List<ReviewDTO> reviews = reviewService.getReviewsByUserId(memberId);

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

	// 리뷰 삭제 API
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
		try {
			reviewService.deleteReview(reviewId); // 리뷰 삭제 서비스 호출
			return ResponseEntity.ok("리뷰가 삭제되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 삭제 중 오류가 발생했습니다.");
		}
	}
}
