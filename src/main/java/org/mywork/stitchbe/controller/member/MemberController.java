package org.mywork.stitchbe.controller.member;

import java.util.HashMap;
import java.util.Map;

import org.mywork.stitchbe.dto.AddMemberRequest;
import org.mywork.stitchbe.dto.LoginRequest;
import org.mywork.stitchbe.dto.MemberDto;
import org.mywork.stitchbe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//작성자 : 박주희

@RestController
@RequestMapping("/api")
public class MemberController {

   private final MemberService memberService;
   private final AuthenticationManager authenticationManager;  // 인증 매니저 추가


   // 생성자를 통해 MemberService 주입
   @Autowired
   public MemberController(MemberService memberService, AuthenticationManager authenticationManager) {
      this.memberService = memberService;
      this.authenticationManager = authenticationManager;  // 인증 매니저 주입

   }

   @GetMapping("/login")
   public String memberLoginPage() {
      return "/api/login"; // 실제로는 프론트엔드에서 처리할 경로를 반환
   }

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

         System.out.println("현재 인증된 사용자: " + authentication.getName());

         return ResponseEntity.ok("로그인 성공");
      } catch (AuthenticationException e) {
         // 인증 실패 시 처리
         System.out.println("로그인 실패: " + e.getMessage());
         return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("로그인 실패");
      }
   }

   // 회원가입 처리
   @PostMapping("/signup")
   public ResponseEntity<String> signup(@RequestBody AddMemberRequest addMemberRequest) {
      memberService.save(addMemberRequest);
      return ResponseEntity.ok("회원가입이 완료되었습니다.");
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


   // 특정 회원 정보 조회 API
   @GetMapping("/info/{memberId}")
   public MemberDto getMemberInfo(@PathVariable Long memberId) {
      return memberService.getMemberInfo(memberId); // MemberService를 통해 회원 정보 가져옴
   }
   
   
}