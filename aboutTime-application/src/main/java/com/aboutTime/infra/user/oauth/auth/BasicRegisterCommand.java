package com.aboutTime.infra.user.oauth.auth;

import com.aboutTime.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public abstract class BasicRegisterCommand {
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일을 입력해 주세요.")
    public final String email;

    public BasicRegisterCommand(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public abstract User toUserEntity();
}
