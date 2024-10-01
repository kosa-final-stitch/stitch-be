package org.mywork.stitchbe.controller.member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mywork.stitchbe.Util.JwtUtil;
import org.mywork.stitchbe.dto.AddMemberRequest;
import org.mywork.stitchbe.dto.LoginRequest;
import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.dto.board.CommunityDto;
import org.mywork.stitchbe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.servlet.http.HttpServletResponse;

//작성자 : 박주희

@RestController
@RequestMapping("/api")
public class MemberController {

   private final MemberService memberService;
   private final AuthenticationManager authenticationManager;  // 인증 매니저 추가
   private final JwtUtil jwtUtil;


   // 생성자를 통해 MemberService 주입
   @Autowired
   public MemberController(MemberService memberService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
      this.memberService = memberService;
      this.authenticationManager = authenticationManager;  // 인증 매니저 주입
      this.jwtUtil = jwtUtil;
   }

//   @GetMapping("/login")
//   public String memberLoginPage() {
//      return "/api/login"; // 실제로는 프론트엔드에서 처리할 경로를 반환
//   }

   // 로그인 처리
   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
      try {
         // 수동으로 사용자 인증을 처리하는 부분
         Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
         );

         // 인증된 사용자 정보를 SecurityContext에 저장
         SecurityContextHolder.getContext().setAuthentication(authentication);

         // JWT 토큰 생성
         String jwtToken = jwtUtil.generateToken(authentication);

         // 토큰을 클라이언트에게 응답으로 반환
         Map<String, String> tokenMap = new HashMap<>();
         tokenMap.put("token", jwtToken);

         return ResponseEntity.ok(tokenMap); // JWT 토큰을 클라이언트로 전달
      } catch (AuthenticationException e) {
         // 인증 실패 시 처리
         return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("로그인 실패: " + e.getMessage());
      }
   }

   // 회원가입 처리
   @PostMapping("/signup")
   public ResponseEntity<String> signup(@RequestBody AddMemberRequest addMemberRequest) {
      System.out.println(addMemberRequest);
      memberService.save(addMemberRequest);
      return ResponseEntity.ok("회원가입이 완료되었습니다. ID: " );

   }

   @PostMapping("/validate-email")
   public ResponseEntity<?> validateEmail(@RequestBody Map<String, String> request) {
      String email = request.get("email");
      boolean isEmailValid = memberService.isEmailAvailable(email);
      System.out.println("Email received: " + email);
      Map<String, Boolean> response = new HashMap<>();
      response.put("isValid", isEmailValid);
      System.out.println("Email validation result: " + isEmailValid);
      return ResponseEntity.ok(response);
   }
   @PostMapping("/validate-nickname")
   public ResponseEntity<?> validateNickname(@RequestBody Map<String, String> request) {
      String nickname = request.get("nickname");
      System.out.println("Received nickname: " + nickname);  // 디버그용 로그
      boolean isNicknameValid = memberService.isNicknameAvailable(nickname);
      System.out.println("Nickname validation result: " + isNicknameValid);  // 결과 로그
      Map<String, Boolean> response = new HashMap<>();
      response.put("isValid", isNicknameValid);
      return ResponseEntity.ok(response);
   }

   @PostMapping("/api/check-session")
   public ResponseEntity<?> checkSession() {
      // 현재 세션에서 인증 정보 가져오기
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      // 인증 정보가 있는지 확인
      if (authentication != null && authentication.isAuthenticated()) {
         // 사용자 이름과 권한 확인
         System.out.println("현재 인증된 사용자: " + authentication.getName());
         System.out.println("사용자의 권한: " + authentication.getAuthorities());

         // 인증 객체가 UserDetails 타입인지 확인하고, 사용자 세부 정보 출력
         if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            System.out.println("사용자 이메일: " + user.getUsername());
         }
         return ResponseEntity.ok("세션에 인증된 사용자: " + authentication.getName());
      } else {
         System.out.println("세션에 인증된 사용자가 없습니다.");
         return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("인증되지 않음");
      }
   }

   //   유은 작성 코드
   @GetMapping("/member/info")
   public ResponseEntity<MemberDto> getCurrentMemberInfo() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      System.out.println("현재 인증된 사용자 멤버인포가져오기" + authentication);

      if (authentication != null && authentication.isAuthenticated()) {
         String email = authentication.getName();  // 인증된 사용자 이메일을 가져옴

         // 이메일로 회원 정보 조회
         MemberDto memberDto = memberService.getMemberInfoByEmail(email);

         // 권한 정보를 가져와 SimpleGrantedAuthority로 변환하여 DTO에 설정
         List<GrantedAuthority> grantedAuthorities = authentication.getAuthorities()
                 .stream()
                 .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))  // 권한 변환
                 .collect(Collectors.toList());

         memberDto.setAuthorities(grantedAuthorities);  // 권한 정보를 DTO에 설정

         return ResponseEntity.ok(memberDto);  // 회원 정보를 반환
      } else {
         return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(null);
      }
   }//getCurrentMemberInfo
   
   
   // 회원 정보 업데이트
   @PutMapping("/member/info")
   public ResponseEntity<String> updateMemberInfo(@RequestBody MemberDto memberDto, Authentication authentication) {
       String email = authentication.getName();  // 인증된 사용자의 이메일을 가져옴
       memberService.updateMemberInfo(email, memberDto);
       return ResponseEntity.ok("회원 정보가 업데이트되었습니다.");
   }


   //----------- 호영 작성 코드 -----------//

   ///----- 모든 회원 조회 관리자 전용(호영) -----///
   @PreAuthorize("hasRole('ROLE_ADMIN')")// admin 인 관리자만 볼 수 있게
   @GetMapping("/members")
   public ResponseEntity<List<MemberDto>> getAllMembers() {
      List<MemberDto> members = memberService.getAllMembers(); // Service에서 호출
      return ResponseEntity.ok(members);  // 조회한 모든 회원 정보를 반환
   }
   //----- 회원 정보 삭제 관리자 전용(호영) -----//
   @DeleteMapping("/members/{email}")
   public ResponseEntity<String> deleteMember(@PathVariable String email) {
      try {
         // 해당 이메일의 회원이 있는지 확인
         MemberDto member = memberService.getMemberInfoByEmail(email);
         if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원이 존재하지 않습니다.");
         }

         // 회원 삭제 처리
         memberService.deleteByEmail(email);
         return ResponseEntity.ok("회원이 성공적으로 삭제되었습니다.");
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 삭제 중 오류가 발생했습니다.");
      }
   }
}