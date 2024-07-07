package com.aboutTime.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Users", schema = "about_time")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long id;

    @jakarta.validation.constraints.NotNull
    @Column(name = "device_idx", nullable = false)
    private Long deviceIdx;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "user_type", length = 50)
    private String userType;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "user_id", length = 50)
    private String userId;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "user_mail", length = 50)
    private String userMail;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "user_nickname", length = 50)
    private String userNickname;

    @jakarta.validation.constraints.Size(max = 200)
    @Column(name = "user_image", length = 200)
    private String userImage;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "user_city", length = 50)
    private String userCity;

    @Column(name = "sel_time")
    private LocalDate selTime;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "sel_days", length = 50)
    private String selDays;

    @jakarta.validation.constraints.NotNull
    @Column(name = "del_flag", nullable = false)
    private Byte delFlag;

    @jakarta.validation.constraints.NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}