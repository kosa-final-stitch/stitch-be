package org.mywork.stitchbe.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AcademyDTO {
    private Long academyId;
    private String academyName;
    private String address;
    private String phone;
    private String email;
    private String siteAddress;
    private LocalDate regdate;
    private LocalDate editdate;
    private String apiAcademyId;
}
