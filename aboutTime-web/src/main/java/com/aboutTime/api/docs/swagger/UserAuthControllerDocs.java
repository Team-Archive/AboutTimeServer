package com.aboutTime.api.docs.swagger;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserAuthControllerDocs {
    @Operation(summary = "회원가입")
    @PostMapping
    ResponseEntity<Void> registerUserInfo();

    @Operation(summary = "로그인")
    @PostMapping
    ResponseEntity<Void> loginUserInfo();
}
