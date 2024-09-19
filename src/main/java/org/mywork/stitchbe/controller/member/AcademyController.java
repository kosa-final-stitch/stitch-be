/*
 2024.9.16 박요한 | getTopRatedAcademies 추가
*/

package org.mywork.stitchbe.controller.member;

import java.util.List;
import java.util.Map;

import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.AcademyReviewDTO;
import org.mywork.stitchbe.service.AcademyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // RESTful API를 위한 컨트롤러 클래스
@RequestMapping("/api/academies")  // API의 기본 경로를 설정
public class AcademyController {

    private final AcademyService academyService;

    public AcademyController(AcademyService academyService) {
        this.academyService = academyService;
    }

    // 전체 학원 정보 반환
    @GetMapping("/academy")
    public ResponseEntity<List<AcademyDTO>> getAllAcademy() {
    	List<AcademyDTO> academy = academyService.getAllAcademy();
    	System.out.println("AcademyController: getAllAcademyById 호출됨");
    	return ResponseEntity.ok(academy);
    }
    // 학원 ID에 따른 학원 정보 반환
    @GetMapping("/academy/{academyId}")
    public ResponseEntity<AcademyDTO> getAcademyById(@PathVariable("academyId") Long academyId) {
        AcademyDTO academy = academyService.getAcademyById(academyId);
        System.out.println("AcademyController: getAcademyById 호출됨. ID: " + academyId);
        return ResponseEntity.ok(academy);
    }

    // 학원 ID에 따른 강좌 목록 반환
    @GetMapping("/courses/{academyId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByAcademyId(@PathVariable("academyId") Long academyId) {
        List<CourseDTO> courses = academyService.getCoursesByAcademyId(academyId);
        return ResponseEntity.ok(courses);
    }


    // 완료된 강의 목록 가져오기
    @GetMapping("/academy/{academyId}/completed-courses")
    public ResponseEntity<List<CourseDTO>> getCompletedCourses(@PathVariable Long academyId) {
        List<CourseDTO> completedCourses = academyService.getCompletedCourses(academyId);
        return ResponseEntity.ok(completedCourses);
    }

    // 강의 리뷰 저장
    @PostMapping("/courses/{courseId}/reviews")
    public ResponseEntity<String> saveCourseReview(@PathVariable Long courseId, @RequestBody ReviewDTO review) {
        academyService.saveCourseReview(courseId, review);
        return ResponseEntity.ok("리뷰가 성공적으로 저장되었습니다.");
    }

    // 학원의 별점 및 레이더 차트 데이터 가져오기
    @GetMapping("/academy/{academyId}/rating")
    public ResponseEntity<Map<String, Integer>> getAcademyRating(@PathVariable Long academyId) {
        Map<String, Integer> ratingData = academyService.getAcademyRating(academyId);
        return ResponseEntity.ok(ratingData);
    }
    
    
    
    
    
    //요한
   
    // 홈: 고평점 학원 목록 반환
    @GetMapping("/top")
    public ResponseEntity<List<AcademyReviewDTO>> getTopRatedAcademies() {
        List<AcademyReviewDTO> academy = academyService.getTopRatedAcademies();
        return ResponseEntity.ok(academy);
    }
}
