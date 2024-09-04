package org.mywork.stitchbe.controller.member;

import org.springframework.web.bind.annotation.GetMapping;

public class MemberController {
    @GetMapping("/member/login")
    public String memberLoginPage() {
        return "member/login";
    }
}
