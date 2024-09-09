package org.mywork.stitchbe.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Member implements UserDetails {

    private Long memberId;
    private String email;
    private String password;
    private Set<String> role;  // 역할 필드 추가 (예: ROLE_USER, ROLE_ADMIN 등)

    public Member(Long memberId, String email, String password, Set<String> role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)  // ROLE_ 접두사 확인
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;  // 이메일을 사용자 이름으로 사용
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
