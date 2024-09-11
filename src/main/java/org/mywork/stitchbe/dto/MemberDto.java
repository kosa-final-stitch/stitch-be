package org.mywork.stitchbe.dto;

import lombok.Data;
import java.util.Date;

//작성자 : 박주희

@Data
public class MemberDto {
    private Long memberId;        // 사용자 ID
    private String email;        // 이메일
    private String password;     // 비밀번호
    private String oldPassword;  // 이전 비밀번호
    private String name;         // 이름
    private String nickname;     // 닉네임
    private String address;      // 주소
    private Integer gender;      // 성별 (0 또는 1로 표현될 가능성 있음)
    private Date birth;         // 나이
    private String phone;        // 전화번호
    private Date signupdate;     // 가입일자
    private Date editdate;        // 수정일자
}
