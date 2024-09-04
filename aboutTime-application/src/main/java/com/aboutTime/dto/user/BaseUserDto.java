package com.aboutTime.dto.user;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.domain.user.UserRole;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseUserDto {
    private Long idx;
    private String userMail;
    private UserRole userRole;
    private String userImage;
    private String userNickname;
    private LocalDateTime createdAt;

    // DateTimeFormatter 예시 (원래 코드에서 사용되었을 것으로 추정)
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BaseUserDto(Long idx, String userMail, UserRole userRole, String userImage, String userNickname, LocalDateTime createdAt) {
        this.idx = idx;
        this.userMail = userMail;
        this.userRole = userRole;
        this.userImage = userImage;
        this.userNickname = userNickname;
        this.createdAt = createdAt;
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

    public String getUserImage() {
        return userImage;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getCreatedAt() {
        return dateTimeFormatter.format(createdAt);
    }

    public static BaseUserDto from(BaseUser baseUser) {
        return new BaseUserDto(
                baseUser.getIdx(),
                baseUser.getUserMail(),
                baseUser.getRole(),
                baseUser.getUserImage(),
                baseUser.getUserNickname(),
                baseUser.getCreatedAt()
        );
    }
}
