package org.mywork.stitchbe.controller;

import org.mywork.stitchbe.Util.PasswordEncoderUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//작성자 : 박주희

@RestController
@RequestMapping("/api")
public class PasswordController {
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/encode")
    public String encodePassword(@RequestBody String password) {
        return passwordEncoder.encode(password);
    }
}
