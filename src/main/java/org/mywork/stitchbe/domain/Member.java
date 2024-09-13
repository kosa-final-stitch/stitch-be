package org.mywork.stitchbe.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;

//작성자 : 박주희

@Data
public class Member implements UserDetails {

    private Long memberId;
    private String email;
    private String password;
    private Set<GrantedAuthority> authorities;  // 권한 정보를 저장할 필드 추가

    public Member(Long memberId, String email, String password, Set<GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;  // 권한 정보 초기화
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;  // 권한 정보를 반환
    }

    @Override
    public String getUsername() {
        return email;  // 이메일을 사용자 이름으로 사용
        
    }

    @Override
    public String getPassword() {
        return password;  // 비밀번호 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // 계정 만료 여부 반환 (true는 만료되지 않음을 의미)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // 계정 잠금 여부 반환 (true는 잠기지 않음을 의미)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 자격 증명 만료 여부 반환 (true는 만료되지 않음을 의미)
    }

    @Override
    public boolean isEnabled() {
        return true;  // 계정 활성화 여부 반환 (true는 활성화 상태를 의미)
    }
}