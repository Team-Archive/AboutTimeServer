package com.aboutTime.infra.user.oauth.auth;

import com.aboutTime.domain.OAuthProvider;
import com.aboutTime.domain.UserRole;
import com.aboutTime.entity.User;
import com.aboutTime.common.StringUtils;
import com.aboutTime.entity.UserOAuth;

public class OAuthRegisterCommand extends BasicRegisterCommand {

    private final OAuthProvider provider;

    public OAuthRegisterCommand(String email, OAuthProvider provider) {
        super(email);
        this.provider = provider;
    }
    @Override
    public User toUserEntity() {
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
