package com.aboutTime.dto.user;

import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.val;
@Getter
public class OAuthUserInfoRequestDto {
    @NotBlank(message = "Oauth provider는 필수 값입니다.")
    public String provider;

    @NotBlank(message = "Provider access token은 필수 값입니다.")
    @JsonProperty("providerAccessToken")
    public String token;

    @NotBlank(message = "닉네임은 필수 값입니다.")
    public String nickname;
}
