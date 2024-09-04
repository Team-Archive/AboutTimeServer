package com.aboutTime.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuthLoginRequestDto {
    @NotBlank(message = "Oauth provider는 필수 값입니다.")
    private String provider;

    @NotBlank(message = "Provider access token은 필수 값입니다.")
    @JsonProperty("providerAccessToken")
    private String token;
}
