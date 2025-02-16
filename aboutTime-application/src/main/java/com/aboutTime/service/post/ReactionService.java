package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.domain.user.UserRepository;
import com.aboutTime.dto.post.EmojiReactionRequest;
import com.aboutTime.dto.post.ReactionDto;
import com.aboutTime.entity.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final EmojiService emojiService;

    @Transactional(readOnly = true)
    public List<Reaction> getReactionsForPost(Post post) {
        return reactionRepository.findByPost(post);
    }

    @Transactional
    public ReactionDto reactToPost(Long postId, EmojiReactionRequest request) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 게시글이 없습니다."));

        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 유저가 없습니다."));

        EmojiType emojiType = Arrays.stream(EmojiType.values())
                .filter(e -> e.name().equalsIgnoreCase(request.getEmojiType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이모지 타입입니다."));

        var emoji = emojiService.getEmojiByType(emojiType);

        Reaction existingReaction = reactionRepository.findByPostAndUserAndEmoji(post, user, emoji)
                .orElse(null);

        Reaction reaction;
        if (existingReaction != null) {
            existingReaction.increaseCount();
            reaction = reactionRepository.save(existingReaction);
        } else {
            Reaction newReaction = Reaction.builder()
                    .post(post)
                    .user(user)
                    .emoji(emoji)
                    .count(1) // 첫 번째 반응
                    .build();
            reaction = reactionRepository.save(newReaction);
        }
        return ReactionDto.fromEntity(reaction);
    }

//    @Transactional
//    public void removeReaction(Post post, User user, Emoji emoji) {
//        Reaction reaction = reactionRepository.findByPostAndUserAndEmoji(post, user, emoji)
//                .orElseThrow(() -> new RuntimeException("Reaction not found"));
//        reactionRepository.delete(reaction);
//    }

    public Map<String, Integer> getEmojiStatistics(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 게시글이 없습니다."));

        List<Reaction> reactions = reactionRepository.findByPost(post);

        return reactions.stream()
                .collect(Collectors.toMap(
                        reaction -> reaction.getEmoji().getEmojiType().name(),
                        Reaction::getCount,
                        Integer::sum
                ));
    }

    public Map<Long, Integer> getUserReactionsByEmoji(Long postId, String emojiType) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당하는 게시글이 없습니다."));

        EmojiType emojiType2 = Arrays.stream(EmojiType.values())
                .filter(e -> e.name().equalsIgnoreCase(emojiType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이모지 타입입니다."));

        var emoji = emojiService.getEmojiByType(emojiType2);

        List<Reaction> reactions = reactionRepository.findByPostAndEmoji(post, emoji);

        return reactions.stream()
                .collect(Collectors.toMap(
                        reaction -> reaction.getUser().getIdx(),
                        Reaction::getCount
                ));
    }

}
