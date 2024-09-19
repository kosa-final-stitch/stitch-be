package org.mywork.stitchbe.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String gender;// 성별
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // 프론트엔드에서 전달된 yyyy-MM-dd 형식의 데이터를 매핑
    private Date birth;
    private String phone;        // 전화번호
    private Date signupdate;     // 가입일자
    private Date editdate;        // 수정일자
    
    
 // 비밀번호 변경을 위한 필드
    private String currentPassword;  // 현재 비밀번호
    private String newPassword;      // 새 비밀번호
    private String confirmPassword;  // 새 비밀번호 확인
}
