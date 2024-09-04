package com.aboutTime.service.auth;

import com.aboutTime.dto.user.OAuthLoginRequestDto;
import com.aboutTime.dto.user.OAuthRegisterRequestDto;
import com.aboutTime.dto.user.OAuthUserInfoRequestDto;
import com.aboutTime.infra.user.oauth.provider.OAuthProviderClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthUserService {

    private final List<OAuthProviderClient> oAuthProviderClients;

    public OAuthRegisterRequestDto getOAuthRegisterInfo(OAuthUserInfoRequestDto request) {
        log.debug("oauth provider access token: {}", request);
        var provider = request.getProvider();
        var oAuthProviderClient = getOAuthProviderClient(provider);
        var email = oAuthProviderClient.getEmail(request.getToken());

        OAuthRegisterRequestDto resultDto = new OAuthRegisterRequestDto();
        resultDto.setProvider(oAuthProviderClient.getProvider());
        resultDto.setEmail(email);
        resultDto.setNickname(request.getNickname());

        return resultDto;
    }

    public String getOAuthEmail(OAuthLoginRequestDto request) {
        log.debug("oauth provider access token: {}", request);
        var provider = request.getProvider();
        var oAuthProviderClient = getOAuthProviderClient(provider);
        return oAuthProviderClient.getEmail(request.getToken());
    }

    private OAuthProviderClient getOAuthProviderClient(String provider) {
        return oAuthProviderClients.stream()
                .filter(client -> client.getProvider().getRegistrationId().equals(provider))
                .findFirst()
                .orElseThrow(() -> new ProviderNotFoundException(
                        "There is no suitable register provider client for " + provider));
    }
}
