/*
 2024.9.16. 박요한 | getTopRatedAcademies 추가
*/

package org.mywork.stitchbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;
import org.mywork.stitchbe.dto.home.AcademyReviewDTO;


@Mapper
public interface AcademyMapper {

	//학원정보
    AcademyDTO getAcademyById(Long academyId);

    //학원아이디+강의
    List<CourseDTO> getCoursesByAcademyId(Long academyId);
    
    //전체학원정보
    List<AcademyDTO> getAllAcademy();

    //홈: 고평점 학원 정보
    List<AcademyReviewDTO> getTopRatedAcademies();

}