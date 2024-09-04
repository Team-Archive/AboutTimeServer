package com.aboutTime.dto.user;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.OAuthProvider;
import com.aboutTime.domain.user.UserRole;
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

    public BaseUser toUserEntity() {
        return new UserOAuth(email, UserRole.GENERAL, provider, nickname);
    }
}
