package com.aboutTime.api.user;

import com.aboutTime.config.security.token.jwt.JwtAuthenticationToken;
import com.aboutTime.domain.user.UserInfo;
import com.aboutTime.dto.user.OAuthLoginRequestDto;
import com.aboutTime.dto.user.OAuthRegisterRequestDto;
import com.aboutTime.dto.user.OAuthUserInfoRequestDto;
import com.aboutTime.service.auth.OAuthUserService;
import com.aboutTime.service.user.UserRegisterService;
import com.aboutTime.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;
    private final UserRegisterService userRegisterService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUserInfo(@Validated @RequestBody OAuthUserInfoRequestDto userInfoDto) {
        var registerDto = oAuthUserService.getOAuthRegisterInfo(userInfoDto);
        var userInfo = userRegisterService.registerUser(registerDto);
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(userInfo));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUserInfo(@Validated @RequestBody OAuthLoginRequestDto oAuthLoginRequestDto) {
        var oAuthEmail = oAuthUserService.getOAuthEmail(oAuthLoginRequestDto);
        var user = userService.findUserByEmail(oAuthEmail);
        var userInfo = new UserInfo(user.getUserMail(), user.getUserRole(), user.getIdx());
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(userInfo));
        return ResponseEntity.ok().build();
    }
}
