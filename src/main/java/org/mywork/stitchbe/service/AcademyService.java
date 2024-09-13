package org.mywork.stitchbe.service;

import java.util.List;

import org.mywork.stitchbe.dto.AcademyDTO;
import org.mywork.stitchbe.dto.CourseDTO;
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
}
