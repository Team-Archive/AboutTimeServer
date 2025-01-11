package com.aboutTime.domain.user;

import com.aboutTime.common.StringUtils;
import com.aboutTime.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "user_type")
@SQLDelete(sql = "UPDATE Users SET del_flag = true WHERE user_id=?")
@Where(clause = "del_flag = false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Setter
    private Long idx;

    @Column(name = "user_mail", unique = true)
    private String userMail;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_city")
    private String userCity;

    @Column(name = "sel_time")
    private String selTime;

    @Column(name = "sel_days")
    private String selDays;

    public BaseUser(Long idx) {
        this.idx = idx;
    }

    protected BaseUser(String userMail, UserRole role) {
        this.role = role;
        this.userMail = userMail;
    }

    protected BaseUser(String userMail, UserRole role, String userNickname) {
        this.role = role;
        this.userMail = userMail;
        this.userNickname = userNickname;
    }

    public UserInfo convertToUserInfo() {
        return new UserInfo(userMail, role, idx);
    }

    public String getUserNickname() {
        return userNickname != null ? userNickname : StringUtils.extractIdFromMail(userMail);
    }

    public String getUserImage() {
        return userImage != null ? userImage : "";
    }

    public String getUserType() {
        return PasswordUser.PASSWORD_TYPE;
    }

}
