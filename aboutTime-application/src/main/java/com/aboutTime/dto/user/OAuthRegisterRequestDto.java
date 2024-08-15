package com.aboutTime.dto.user;

import com.aboutTime.domain.OAuthProvider;
import com.aboutTime.domain.UserRole;
import com.aboutTime.entity.User;
import com.aboutTime.entity.UserOAuth;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthRegisterRequestDto {
    public OAuthProvider provider;
    public String email;
    public String nickname;

    public User toUserEntity() {
        return new UserOAuth(email, UserRole.GENERAL, provider, nickname);
    }
}
