package com.aboutTime.infra.user.oauth.auth;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.OAuthProvider;
import com.aboutTime.domain.user.UserRole;
import com.aboutTime.common.StringUtils;
import com.aboutTime.entity.UserOAuth;

public class OAuthRegisterCommand extends BasicRegisterCommand {

    private final OAuthProvider provider;

    public OAuthRegisterCommand(String email, OAuthProvider provider) {
        super(email);
        this.provider = provider;
    }
    @Override
    public BaseUser toUserEntity() {
        return new UserOAuth(
                email,
                UserRole.GENERAL,
                provider,
                StringUtils.extractIdFromMail(email)
        );
    }

    public OAuthProvider getProvider() {
        return provider;
    }
}
