package org.mywork.stitchbe.dto;


import lombok.Data;


@Data
public class ApiCourseData {
    private String trainstCstId;  // 학원 ID
    private String trprId;        // 과정 ID
    private String subTitle;      // 학원명
    private String title;         // 과정명
    private String traStartDate;
    private String traEndDate;
    private String trprDegr;
    private String telNo;
    private String subTitleLink;
    private String titleLink;
}
