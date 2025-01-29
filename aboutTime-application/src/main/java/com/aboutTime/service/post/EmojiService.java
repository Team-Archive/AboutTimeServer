package com.aboutTime.service.post;

import com.aboutTime.entity.post.Emoji;
import com.aboutTime.entity.post.EmojiRepository;
import com.aboutTime.entity.post.EmojiType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmojiService {

    private final EmojiRepository emojiRepository;

    public Emoji getEmojiByType(EmojiType emojiType) {
        return emojiRepository.findByEmojiType(emojiType);
    }
}
