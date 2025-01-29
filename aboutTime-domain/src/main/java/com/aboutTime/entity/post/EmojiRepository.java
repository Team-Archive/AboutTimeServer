package com.aboutTime.entity.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Emoji findByEmojiType(EmojiType emojiType);

}
