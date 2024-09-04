package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

//작성자 : 박주희
@Data
public class AdminDto {
    private Long admin_id;         // 관리자 id
    private String password;       // 비밀번호
    private String name;           // 이름
    private String role;           // 역할
    private String status;         // 상태
    private Date created_at;       // 생성일자
    private Date updated_at;       // 수정일자
}
