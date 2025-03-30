package com.aboutTime.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "emoji")
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emoji_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "emoji_type", nullable = false, unique = true)
    private EmojiType emojiType;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Builder
    public Emoji(EmojiType emojiType, String imageUrl) {
        this.emojiType = emojiType;
        this.imageUrl = imageUrl;
    }

}
