package org.mywork.stitchbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;

@Mapper
public interface AcademyMapper {

    @Select("SELECT * FROM academy WHERE academy_id = #{academyId}")
    AcademyDTO getAcademyById(Long academyId);

    @Select("SELECT * FROM course WHERE academy_id = #{academyId}")
    List<CourseDTO> getCoursesByAcademyId(Long academyId);
}