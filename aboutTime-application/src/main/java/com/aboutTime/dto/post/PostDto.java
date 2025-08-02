package com.aboutTime.dto.post;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.entity.post.Post;
import com.aboutTime.entity.post.Reaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PostDto {

    private Long postId;
    private LocalDateTime createdAt;
    private String mainImage;
    private String mainComment;
    private Long authorId;
    private Double currentTemperature;
    private String weatherIcon;

    private List<PostImageDto> postImages;
    private List<ReactionListDto> reactions;

    public Post toEntity(BaseUser author) {
        return Post.builder()
                .mainImage(mainImage)
                .mainComment(mainComment)
                .author(author)
                .currentTemperature(currentTemperature)
                .weatherIcon(weatherIcon)
                .build();
    }

    public static PostDto specificForm(Post post) {
        List<PostImageDto> imageDtos = post.getPostImages().stream()
                .map(PostImageDto::from)
                .collect(Collectors.toList());

        return new PostDto(
                post.getId(),
                post.getCreatedAt(),
                post.getMainImage(),
                post.getMainComment(),
                post.getAuthor().getIdx(),
                post.getCurrentTemperature(),
                post.getWeatherIcon(),
                imageDtos,
                getReactionDtos(post)
        );
    }

    public static PostDto simpleForm(Post post) {
        return new PostDto(
                post.getId(),
                post.getCreatedAt(),
                post.getMainImage(),
                post.getMainComment(),
                post.getAuthor().getIdx(),
                post.getCurrentTemperature(),
                post.getWeatherIcon(),
                null,
                getReactionDtos(post)
        );
    }

    private static List<ReactionListDto> getReactionDtos(Post post) {
        return post.getReactions().stream()
                .collect(Collectors.groupingBy(
                        reaction -> reaction.getEmoji().getEmojiType(),  // EmojiType 기준 그룹화
                        Collectors.summingInt(Reaction::getCount)   // count 합산
                ))
                .entrySet().stream()
                .map(entry -> new ReactionListDto(entry.getKey(), entry.getValue())) // Map → List 변환
                .sorted(Comparator.comparingInt(ReactionListDto::getCount).reversed()) // count 기준 내림차순 정렬
                .collect(Collectors.toList());
    }

}
