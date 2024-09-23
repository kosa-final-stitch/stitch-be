/*
 2024.9.17. 박요한 | getTopRatedCourses 추가
 */

package org.mywork.stitchbe.service;

import java.util.List;

import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.CourseReviewDTO;
import org.mywork.stitchbe.mapper.CourseMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    // 의존성 주입 (Constructor Injection)
    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    // 학원 ID와 코스 ID에 따라 코스 상세 정보를 가져오는 메서드
    public CourseDTO getCourseById(Long academyId, Long courseId) {
        return courseMapper.getCourseById(academyId, courseId);
    }

    // 강의 ID에 따른 리뷰 목록을 가져오는 메서드
    public List<ReviewDTO> getReviewsByCourseId(Long courseId) {
   	 List<ReviewDTO> reviews = courseMapper.getReviewsByCourseId(courseId);
	 System.out.println(" 특정 강의에 리뷰리스트 : "+reviews);
	 for (ReviewDTO review : reviews) {
		    System.out.println("리뷰 작성자: " + review.getMemberId()
	 +" 코스리뷰 getAtmosphere : " + review.getAtmosphere()
	 +" 코스리뷰 getAtmosphereRating : "+ review.getAtmosphereRating()
	 +" 코스리뷰 getManagementRating : "	 + review.getManagementRating()
	 +" 코스리뷰 getCourseId : "	 + review.getCourseId()
	 +" 코스리뷰 getReviewId : " + review.getReviewId()
	 +" 코스리뷰 getRegDate : "	 + review.getRegDate());
		}
        return reviews; // 매퍼를 통해 리뷰 데이터를 가져옴
    }

    //전체 강의
	public List<CourseDTO> getAllCourses() {
		System.out.println("코스서비스 getAllCourses");
		return courseMapper.getAllCourses();
	}

	public CourseDTO getCourseByCourseId(Long courseId) {
		System.out.println("코스서비스 getCourseByCourseId");
        return courseMapper.getCourseByCourseId(courseId);
	}

    // 홈: 평점이 높은 교육 과정을 가져오는 메서드
    public List<CourseReviewDTO> getTopRatedCourses() {
        return courseMapper.getTopRatedCourses();
    }
}
