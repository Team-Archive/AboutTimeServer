package com.aboutTime.dto.user;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.PasswordUser;
import com.aboutTime.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PasswordRegisterRequestDto {
    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "올바른 이메일을 입력해 주세요.")
    private String email;

    @NotBlank(message = "닉네임은 필수 값입니다.")
    private String nickname;

    @NotBlank(message = "패스워드는 필수 값입니다.")
    @Pattern(
            regexp = "(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d@$!%*#?&]{8,20}$",
            message = "비밀번호는 영문/숫자 를 꼭 포함하여 8~20자리로 입력해 주세요."
    )
    private String password;

    public BaseUser toUserEntity() {
        return new PasswordUser(email, UserRole.GENERAL, password, nickname);
    }

    public void updatePasswordToEncrypt(String encryptPassword) {
        this.password = encryptPassword;
    }
}
