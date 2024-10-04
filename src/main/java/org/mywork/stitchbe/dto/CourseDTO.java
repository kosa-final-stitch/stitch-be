/*
2024.9.29. 박요한 | 학원명, 평점 관련 필드 추가
 */
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
	private String academy_name;             // 학원명

	// 평점 관련 필드 추가
	private Double average_rating;           // 평균 평점
	private Double education_rating;         // 교육 평점
	private Double instructor_rating;        // 강사 평점
	private Double facility_rating;          // 시설 평점
	private Double atmosphere_rating;        // 분위기 평점
	private Double management_rating;        // 운영 평점
	private Double later_rating;             // 사후 평점
}
