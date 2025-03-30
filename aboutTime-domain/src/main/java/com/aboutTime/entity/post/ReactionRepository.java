package com.aboutTime.entity.post;

import com.aboutTime.domain.user.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    Optional<Reaction> findByPostAndUserAndEmoji(Post post, BaseUser user, Emoji emoji);

    List<Reaction> findByPostAndEmoji(Post post, Emoji emoji);

    List<Reaction> findByPost(Post post);

}
