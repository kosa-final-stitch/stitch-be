package org.mywork.stitchbe.controller.admin;


import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }
}
