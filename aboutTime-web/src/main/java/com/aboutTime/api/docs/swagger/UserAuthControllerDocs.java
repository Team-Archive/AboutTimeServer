package com.aboutTime.api.docs.swagger;

import com.aboutTime.dto.user.OAuthLoginRequestDto;
import com.aboutTime.dto.user.OAuthUserInfoRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

public interface UserAuthControllerDocs {
    @Operation(summary = "[NoAuth] 회원가입", description = "소셜 사용자 회원 가입.")
    ResponseEntity<Void> registerUserInfo(OAuthUserInfoRequestDto userInfoDto);

    @Operation(summary = "[NoAuth] 로그인", description = "소셜 사용자 로그인.")
    ResponseEntity<Void> loginUserInfo(OAuthLoginRequestDto oAuthLoginRequestDto);
}
