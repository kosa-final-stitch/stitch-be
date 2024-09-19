/*
 2024.9.16. 박요한 | getTopRatedAcademies 추가
*/

package org.mywork.stitchbe.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.AcademyReviewDTO;


@Mapper
public interface AcademyMapper {

	//학원정보
    AcademyDTO getAcademyById(Long academyId);

    //학원아이디+강의
    List<CourseDTO> getCoursesByAcademyId(Long academyId);
    
    //전체학원정보
    List<AcademyDTO> getAllAcademy();

    // 완료된 강의 목록 조회
    List<CourseDTO> findCompletedCourses(@Param("academyId") Long academyId);

    // 강의 리뷰 삽입
    void insertCourseReview(@Param("courseId") Long courseId, @Param("review") ReviewDTO review);

    // 학원 별점 및 레이더 차트 데이터 조회
    Map<String, Integer> findAcademyRating(@Param("academyId") Long academyId);
    
    
    //요한
    //홈: 고평점 학원 정보
    List<AcademyReviewDTO> getTopRatedAcademies();

}