package com.aboutTime.infra.user.oauth.provider;

import com.aboutTime.domain.OAuthProvider;
import com.aboutTime.infra.user.oauth.auth.OAuthRegisterCommand;

public interface OAuthProviderClient {
    OAuthProvider getProvider();

    OAuthRegisterCommand getOAuthRegisterInfo(String accessToken);

    String getEmail(String accessToken);
}
