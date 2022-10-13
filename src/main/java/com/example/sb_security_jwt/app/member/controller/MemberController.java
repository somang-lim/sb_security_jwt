package com.example.sb_security_jwt.app.member.controller;

import com.example.sb_security_jwt.app.member.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return "username : %s, password : %s".formatted(loginDto.getUsername(), loginDto.getPassword());
    }

    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }
}
