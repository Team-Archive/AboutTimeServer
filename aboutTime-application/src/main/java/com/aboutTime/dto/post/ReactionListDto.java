package com.aboutTime.dto.post;

import com.aboutTime.entity.post.EmojiType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReactionListDto {

    private EmojiType emojiType;
    private int count;

}