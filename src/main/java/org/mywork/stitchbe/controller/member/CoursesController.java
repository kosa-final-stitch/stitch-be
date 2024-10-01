/*
 2024.9.17. 박요한 | getTopRatedCourses 추가
 */

package org.mywork.stitchbe.controller.member;

import java.util.List;

import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.CourseReviewDTO;
import org.mywork.stitchbe.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // 강의와 관련된 API는 /api/courses 경로 사용
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }
    // 전체 강의 정보 반환
    @GetMapping("/academies/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> course = courseService.getAllCourses();
        System.out.println("CoursesController: getAllCourses 호출됨");
        return ResponseEntity.ok(course);
    }

    // 강의 ID로만 강의 정보 반환
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO> getCourseByCourseId(@PathVariable("courseId") Long courseId) {
        CourseDTO course = courseService.getCourseByCourseId(courseId);
        System.out.println("CoursesController: getCourseByCourseId 호출됨");
        return ResponseEntity.ok(course);
    }

    // 학원 ID와 코스 ID에 따른 코스 상세 정보 반환
    @GetMapping("/academies/academy/{academyId}/courses/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("academyId") Long academyId, @PathVariable("courseId") Long courseId) {
        CourseDTO course = courseService.getCourseById(academyId, courseId);
        System.out.println("CoursesController: getCourseById 호출됨. AcademyID: " + academyId + ", CourseID: " + courseId);
        return ResponseEntity.ok(course);
    }

    // 강의 ID에 따른 리뷰 목록 반환
    @GetMapping("courses/{courseId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByCourseId(@PathVariable("courseId") Long courseId) {
        List<ReviewDTO> reviews = courseService.getReviewsByCourseId(courseId);
        System.out.println("강의 ID에 따른 리뷰 목록 반환"+reviews);
        return ResponseEntity.ok(reviews);
    }


    // 홈: 평점이 높은 교육 과정을 반환하는 API 엔드포인트
    @GetMapping("/courses/top")
    public ResponseEntity<List<CourseReviewDTO>> getTopRatedCourses() {
        List<CourseReviewDTO> courses = courseService.getTopRatedCourses();
        return ResponseEntity.ok(courses);
    }
}