package com.aboutTime.dto.user;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.UserRole;

import java.time.format.DateTimeFormatter;

public class SpecificUserDto {
    private Long idx;
    private String userMail;
    private UserRole userRole;
    private String createdAt;
    private String userImage;
    private String userNickname;

    // DateTimeFormatter 예시 (원래 코드에서 사용되었을 것으로 추정)
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SpecificUserDto(Long idx, String userMail, UserRole userRole, String createdAt, String userImage, String userNickname) {
        this.idx = idx;
        this.userMail = userMail;
        this.userRole = userRole;
        this.createdAt = createdAt;
        this.userImage = userImage;
        this.userNickname = userNickname;
    }

    public Long getIdx() {
        return idx;
    }

    public String getUserMail() {
        return userMail;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public static SpecificUserDto from(BaseUser baseUser) {
        String createdAt = dateTimeFormatter.format(baseUser.getCreatedAt());
        return new SpecificUserDto(
                baseUser.getIdx(),
                baseUser.getUserMail(),
                baseUser.getRole(),
                createdAt,
                baseUser.getUserImage(),
                baseUser.getUserNickname()
        );
    }
}
