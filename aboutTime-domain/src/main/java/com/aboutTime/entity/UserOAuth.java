package com.aboutTime.entity;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.OAuthProvider;
import com.aboutTime.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User_oauth")
@DiscriminatorValue(UserOAuth.OAUTH_TYPE)
@NoArgsConstructor
public class UserOAuth extends BaseUser {
    public static final String OAUTH_TYPE = "oauth";

    @Getter
    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    public UserOAuth(String userMail, UserRole userRole, OAuthProvider provider, String userNickname) {
        super(userMail, userRole, userNickname);
        this.oAuthProvider = provider;
    }

    @Override
    public String getUserType() {
        return this.getOAuthProvider().getRegistrationId();
    }

}
