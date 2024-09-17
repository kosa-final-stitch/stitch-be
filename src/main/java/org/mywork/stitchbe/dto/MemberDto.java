package org.mywork.stitchbe.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//작성자 : 박주희

@Data
public class MemberDto implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;  // 직렬화 버전 UID 추가

    private Long memberId;        // 사용자 ID
    private String email;        // 이메일
    private String password;     // 비밀번호
    private String oldPassword;  // 이전 비밀번호
    private String name;         // 이름
    private String nickname;     // 닉네임
    private String address;      // 주소
    private String gender;       // 성별
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // 프론트엔드에서 전달된 yyyy-MM-dd 형식의 데이터를 매핑
    private Date birth;          // 생일
    private String phone;        // 전화번호
    private Date signupdate;     // 가입일자
    private Date editdate;       // 수정일자

    private List<GrantedAuthority> authorities; // 타입을 SimpleGrantedAuthority 대신 GrantedAuthority로 변경

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;  // email을 사용자명으로 사용
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // 계정 만료 여부 (true = 만료되지 않음)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // 계정 잠금 여부 (true = 잠기지 않음)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 비밀번호 만료 여부 (true = 만료되지 않음)
    }

    @Override
    public boolean isEnabled() {
        return true;  // 계정 활성화 여부 (true = 활성화됨)
    }
}