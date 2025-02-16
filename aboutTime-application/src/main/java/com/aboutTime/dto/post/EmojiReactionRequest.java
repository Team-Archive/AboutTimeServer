package com.aboutTime.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmojiReactionRequest {

    private Long userId;
    private String emojiType;

}
