package com.aboutTime.domain.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    private String userMail;
    private UserRole userRole;
    private long idx;
}
