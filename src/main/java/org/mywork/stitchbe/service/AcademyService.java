/*
 2024.9.16. 박요한 | getTopRatedAcademies 추가
 */

package org.mywork.stitchbe.service;

import java.util.List;
import java.util.Map;

import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.AcademyReviewDTO;
import org.mywork.stitchbe.mapper.AcademyMapper;
import org.springframework.stereotype.Service;

@Service
public class AcademyService {

    private final AcademyMapper academyMapper;

    public AcademyService(AcademyMapper academyMapper) {
        this.academyMapper = academyMapper;
    }
    //학원특정
    public AcademyDTO getAcademyById(Long academyId) {
        return academyMapper.getAcademyById(academyId);
    }

    //학원+강의
    public List<CourseDTO> getCoursesByAcademyId(Long academyId) {
        return academyMapper.getCoursesByAcademyId(academyId);
    }
    
    //전체학원
	public List<AcademyDTO> getAllAcademy() {
		 return academyMapper.getAllAcademy();
	}
	
    // 완료된 강의 목록 가져오기
    public List<CourseDTO> getCompletedCourses(Long academyId) {
        return academyMapper.findCompletedCourses(academyId);
    }

    // 강의 리뷰 저장
    public void saveCourseReview(Long courseId, ReviewDTO review) {
        academyMapper.insertCourseReview(courseId, review);
    }

    // 학원의 별점 및 레이더 차트 데이터 가져오기 (정수로 반환)
//    public Map<String, Integer> getAcademyRating(Long academyId) {
//        return academyMapper.findAcademyRating(academyId);
//    }
    
    // 학원 ID로 해당 학원의 평균 별점을 정수로 계산
    public int getAcademyAverageRating(Long academyId) {
        Integer averageRating = academyMapper.findAcademyAverageRating(academyId);
        return (averageRating != null) ? averageRating : 0;
    }
	
    //홈: 고평점 학원
    public List<AcademyReviewDTO> getTopRatedAcademies() {
        return academyMapper.getTopRatedAcademies();
    }
    
    

}
