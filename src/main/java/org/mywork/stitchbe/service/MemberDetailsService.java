package org.mywork.stitchbe.service;

import lombok.RequiredArgsConstructor;
import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.mapper.MemberMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

//작성자 : 박주희

@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper; //회원정보를 DB에서 조회하기 위해 사용
    private final BCryptPasswordEncoder passwordEncoder; //비밀번호를 암호화하거나, 주어진 비밀번호가 저장된 암호화된 비밀번호와 일치하는지 확인하는 데 사용

    public MemberDetailsService(@Lazy BCryptPasswordEncoder passwordEncoder, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }

    // 1. loadUserByUsername 메서드를 추가하여 Spring Security 통합 가능하게 만들기
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //주어진 이메일을 통해 사용자 정보를 조회하고 인증 과정에서 사용할 수 있도록 'UserDetails' 객체로 반환
        //이 값을 이용해 사용자를 db에서 조회
        System.out.println("Loading user by email: " + email);
        MemberDto memberDto = memberMapper.findByEmail(email) //주어진 이메일로 조회 후 dto 객체에 저장
                .orElseThrow(() -> new UsernameNotFoundException("Member not found with email: " + email));
        //만약 존재하지 않으면

        return new User( //조회된 dto 정보 바탕으로 시큐리티의 'userdetails'객체인 'user'객체 생성
                memberDto.getEmail(), //사용자의 이메일을 'user'객체의 사용자명으로 설정
                memberDto.getPassword(), //사용자의 암호화된 비밀번호 설정
                Collections.singletonList(new SimpleGrantedAuthority(memberDto.getRole()))  // 권한 리스트를 설정 (필요 시 추가)
        );
    }

    // 2. 기존 authenticateMember 메서드 유지
//    public MemberDto authenticateMember(String email, String password) { //입력받은 이메일과 비밀번호를 사용해 인증하는 메서드
//        MemberDto memberDto = memberMapper.findByEmail(email) //이메일에 해당하는 사용자 조회
//                .orElseThrow(() -> new IllegalArgumentException("Member not found with email: " + email));
//
//        if (!passwordEncoder.matches(password, memberDto.getPassword())) { //사용자가 입력한 비밀번호가 데이터베이스에 저장된 암호화된 비밀번호와 일치하는지 확인
//            throw new IllegalArgumentException("Invalid password");
//        }
//
//        return memberDto; //인증 성공 시 반환
//    }
}
