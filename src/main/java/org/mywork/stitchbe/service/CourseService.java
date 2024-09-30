/*
 2024.9.17. 박요한 | getTopRatedCourses 추가
 2024.9.29. 박요한 | getPagedCoursesWithStatusAndRating 추가
*/

package org.mywork.stitchbe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.ReviewDTO;
import org.mywork.stitchbe.dto.home.CourseReviewDTO;
import org.mywork.stitchbe.mapper.CourseMapper;
import org.mywork.stitchbe.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseMapper courseMapper;
    private final ReviewMapper reviewMapper;

    // 의존성 주입 (Constructor Injection)
    public CourseService(CourseMapper courseMapper, ReviewMapper reviewMapper) {
        this.courseMapper = courseMapper;
        this.reviewMapper = reviewMapper;
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


    // 학원에 속한 모든 강의의 항목별 평균 평점 계산
    public CourseReviewDTO getAcademyCoursesAverageRating(Long academyId) {
        List<CourseReviewDTO> courses = courseMapper.getCoursesByAcademyId(academyId);

        // 6가지 항목에 대한 총합을 계산
        double totalEducation = 0;
        double totalInstructor = 0;
        double totalFacility = 0;
        double totalAtmosphere = 0;
        double totalManagement = 0;
        double totalLater = 0;
        int totalCourses = courses.size();

        for (CourseReviewDTO course : courses) {
            totalEducation += course.getEducationRating();
            totalInstructor += course.getInstructorRating();
            totalFacility += course.getFacilityRating();
            totalAtmosphere += course.getAtmosphereRating();
            totalManagement += course.getManagementRating();
            totalLater += course.getLaterRating();
        }

        // 평균 계산
        CourseReviewDTO averageRatings = new CourseReviewDTO();
        averageRatings.setEducationRating(totalEducation / totalCourses);
        averageRatings.setInstructorRating(totalInstructor / totalCourses);
        averageRatings.setFacilityRating(totalFacility / totalCourses);
        averageRatings.setAtmosphereRating(totalAtmosphere / totalCourses);
        averageRatings.setManagementRating(totalManagement / totalCourses);
        averageRatings.setLaterRating(totalLater / totalCourses);

        return averageRatings;
    }

    // 박요한
    // 리팩토링: 전체 강의 목록 + 페이지네이션, 정렬 + 진행 구분
    public Map<String, Object> getPagedCoursesWithStatusAndRating(String status, int pageNumber, int pageSize) {
        Map<String, Object> result = new HashMap<>();

        // 전체 데이터 개수에서 총 페이지 수 계산
        int totalCount = courseMapper.getTotalCourseCount(status);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // 페이징 처리된 데이터 가져오기
        int offset = (pageNumber - 1) * pageSize;
        List<CourseDTO> courses = courseMapper.getPagedCoursesWithStatusAndRating(status, offset, pageSize);

        // 결과 맵에 담아서 반환
        result.put("courses", courses);
        result.put("totalPages", totalPages);
        result.put("totalCount", totalCount);  // 총 데이터 개수도 필요시 반환 가능

        return result;
    }

    // 홈: 평점이 높은 교육 과정을 가져오는 메서드
    public List<CourseReviewDTO> getTopRatedCourses() {
        return courseMapper.getTopRatedCourses();
    }


}