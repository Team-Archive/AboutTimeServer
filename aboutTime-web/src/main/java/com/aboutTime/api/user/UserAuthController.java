package com.aboutTime.api.user;

import com.aboutTime.dto.user.OAuthRegisterRequestDto;
import com.aboutTime.dto.user.OAuthUserInfoRequestDto;
import com.aboutTime.service.auth.OAuthUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "user-auth-controller", description = "회원정보 관련 API 목록")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final OAuthUserService oAuthUserService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUserInfo(@Validated @RequestBody OAuthUserInfoRequestDto userInfoDto) {
        OAuthRegisterRequestDto registerDto = oAuthUserService.getOAuthRegisterInfo(userInfoDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUserInfo() {
        return ResponseEntity.ok().build();
    }
}
