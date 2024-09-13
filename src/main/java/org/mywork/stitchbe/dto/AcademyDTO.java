package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

@Data
public class AcademyDTO {
	private Long academy_id;
	private String academy_name;
	private String address;
	private String phone;
	private String email;
	private String site_address;
	private Date regdate;
	private String api_academy_id;

}
