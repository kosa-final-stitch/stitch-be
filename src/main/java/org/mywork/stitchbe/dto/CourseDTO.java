package org.mywork.stitchbe.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseDTO {
    private Long courseId;
    private Long academyId;  // 외래키로 academy_id 참조
    private String courseName;
    private String titleLink;
    private String lector;
    private LocalDate regdate;
    private LocalDate editdate;
    private String apiCourseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer sessionNumber;
}
