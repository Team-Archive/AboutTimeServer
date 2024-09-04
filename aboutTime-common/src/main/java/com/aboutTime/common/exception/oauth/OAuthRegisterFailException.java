package com.aboutTime.common.exception.oauth;

import com.aboutTime.common.exception.BaseException;
import com.aboutTime.common.exception.ExceptionCode;

public class OAuthRegisterFailException extends BaseException {
    public OAuthRegisterFailException(String oAuthProviderRegistrationId, String message) {
        super(oAuthProviderRegistrationId + " : " + message, ExceptionCode.REGISTER_FAIL);
    }
}
