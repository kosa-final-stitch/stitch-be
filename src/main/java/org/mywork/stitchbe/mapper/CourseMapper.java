/*
 2024.9.17. 박요한 | getTopRatedCourses 추가
 2024.9.29 박요한 | getPagedCoursesWithStatusAndRating, getTotalCourseCount 추가
*/

package org.mywork.stitchbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.CourseReviewDTO;


@Mapper // MyBatis 매퍼 인터페이스로서 설정
public interface CourseMapper {

    // 강의 ID에 따른 리뷰 목록을 가져오는 메서드
    List<ReviewDTO> getReviewsByCourseId(@Param("courseId") Long courseId);
    
    // 학원 ID와 코스 ID에 따른 코스 정보 가져오기
    CourseDTO getCourseById(@Param("academyId") Long academyId, @Param("courseId") Long courseId);
    
    //전체강의 가져오기
	List<CourseDTO> getAllCourses();

    // 특정 강의 선택
    CourseDTO getCourseByCourseId(@Param("courseId") Long courseId);


	List<CourseReviewDTO> getCoursesByAcademyId(Long academyId);

    // 박요한
    // 리팩토링: 전체 강의 목록 + 페이지네이션, 정렬 + 진행 구분
    List<CourseDTO> getPagedCoursesWithStatusAndRating(
            @Param("status") String status,     // 상태 (upcoming, ongoing, completed)
            @Param("offset") int offset,        // 페이지네이션을 위한 offset
            @Param("pageSize") int pageSize     // 페이지 크기
    );

    // 총 데이터 개수를 구하는 메서드
    int getTotalCourseCount(@Param("status") String status);

    // 홈: 고평점 강의 정보
    List<CourseReviewDTO> getTopRatedCourses();
}