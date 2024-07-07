package com.aboutTime.api.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "user-auth-controller", description = "회원정보 관련 API 목록")
@RequestMapping("/api/auth")
public class UserAuthController {

    @PostMapping("/register")
    public ResponseEntity<Void> registerUserInfo() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUserInfo() {
        return ResponseEntity.ok().build();
    }
}
