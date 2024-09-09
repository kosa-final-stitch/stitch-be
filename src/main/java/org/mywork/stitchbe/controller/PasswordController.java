package org.mywork.stitchbe.controller;

import org.mywork.stitchbe.Util.PasswordEncoderUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    @GetMapping("/encode")
    public String encodePassword(@RequestParam String password) {
        return PasswordEncoderUtil.encodePassword(password);
    }

    @GetMapping("/match")
    public boolean matchPasswords(@RequestParam String rawPassword, @RequestParam String encodedPassword) {
        return PasswordEncoderUtil.matches(rawPassword, encodedPassword);
    }
}
