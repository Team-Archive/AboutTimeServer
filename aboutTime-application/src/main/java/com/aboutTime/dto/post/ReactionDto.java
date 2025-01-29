package com.aboutTime.dto.post;

import com.aboutTime.entity.post.EmojiType;
import com.aboutTime.entity.post.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReactionDto {

    private Long postId;
    private Long userId;
    private EmojiType emojiType;
    private int count;

    public static ReactionDto fromEntity(Reaction reaction) {
        return new ReactionDto(
                reaction.getPost().getId(),
                reaction.getUser().getIdx(),
                reaction.getEmoji().getEmojiType(),
                reaction.getCount()
        );
    }
}