package com.aboutTime.domain.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BaseUser, Long> {
    Optional<BaseUser> findByUserMail(String userMail);

    Optional<BaseUser> findByUserNickname(String userNickname);

    @Modifying
    @Query("update BaseUser u set u.isDeleted = true where u.idx = :idx")
    void deleteById(@NotNull @Param("idx") Long idx);

    @Modifying
    @Query("update BaseUser u set u.userImage = :userImage where u.idx = :idx")
    void updateUserProfileImage(@Param("idx") Long idx,
                                @Param("userImage") String userImage);

    @Modifying
    @Query("update BaseUser u set u.userNickname = :userNickname where u.idx = :idx")
    void updateNickName(@Param("idx") Long idx,
                        @Param("userNickname") String userNickname);
}
