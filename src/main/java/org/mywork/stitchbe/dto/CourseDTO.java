package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CourseDTO {
	private Long course_id;
	private Long academy_id;
	private String course_name;
	private String title_link;
	private String lector;
	private Date start_date;
	private Date end_date;
	private int session_number;

	 // 학원 이름 필드 추가
    private String academy_name;
	
}
