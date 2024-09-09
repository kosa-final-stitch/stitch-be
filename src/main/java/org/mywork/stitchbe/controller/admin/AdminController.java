package org.mywork.stitchbe.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "redirect:/login";
    }
    @GetMapping("/admin/signup")
    public String adminSignupPage() {
        // Vue.js의 관리자 회원가입 페이지로 리다이렉트
        return "redirect:/signup";  // Vue.js에서 /signup 경로를 처리
    }
}

