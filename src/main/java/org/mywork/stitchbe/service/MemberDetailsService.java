package org.mywork.stitchbe.service;

import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//작성자 : 박주희

@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper; //회원정보를 DB에서 조회하기 위해 사용
    private final BCryptPasswordEncoder passwordEncoder;
    //비밀번호를 암호화하거나, 주어진 비밀번호가 저장된 암호화된 비밀번호와 일치하는지 확인하는 데 사용

    public MemberDetailsService(@Lazy BCryptPasswordEncoder passwordEncoder, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //주어진 이메일을 통해 사용자 정보를 조회하고 인증 과정에서 사용할 수 있도록 'UserDetails' 객체로 반환
        //이 값을 이용해 사용자를 db에서 조회
        System.out.println("Loading user by email: " + email);
        // 이메일로 사용자를 조회하는 로그 추가
        MemberDto memberDto = memberMapper.findByEmail(email) //주어진 이메일로 조회 후 dto 객체에 저장
                .orElseThrow(() -> new UsernameNotFoundException("Member not found with email: " + email));
        //만약 존재하지 않으면


        // AUTH 테이블에서 권한을 조회 (MemberMapper에 메서드 추가)
        List<String> authorities = memberMapper.findAuthoritiesByEmail(email);
//        authorities.forEach(authority -> System.out.println("Authority: " + authority));  // 권한 로그 출력


        // 권한을 SimpleGrantedAuthority로 변환
        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // 디버깅: 불러온 정보 출력
        System.out.println("불러온 이메일: " + memberDto.getEmail());
        System.out.println("불러온 비밀번호: " + memberDto.getPassword());
        System.out.println("권한: " + grantedAuthorities);

        return new org.springframework.security.core.userdetails.User(
                memberDto.getEmail(),
                memberDto.getPassword(),
                grantedAuthorities
        );
    }
}

