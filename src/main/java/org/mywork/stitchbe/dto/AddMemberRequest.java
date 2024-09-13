package org.mywork.stitchbe.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//작성자 : 박주희

@Data
public class AddMemberRequest {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String address;      // 주소
    private String gender;      // 성별 (0 또는 1로 표현)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;  // 서버가 yyyy-MM-dd 형식으로 수신
    private String phone;
}
