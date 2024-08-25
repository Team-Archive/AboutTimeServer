package com.aboutTime.dto.post;

import com.aboutTime.entity.post.Post;
import com.aboutTime.entity.post.Reaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private Double currentTemperature = 29.5;
    private String weatherIcon = "http://openweathermap.org/img/wn/01d@2x.png";

    private List<PostImageDto> postImages;
    private List<Reaction> reactions = new ArrayList<>();

    public Post toEntity(Long authorId) {
        return Post.builder()
                .mainImage(mainImage)
                .mainComment(mainComment)
                .authorId(authorId)
                .currentTemperature(currentTemperature)
                .weatherIcon(weatherIcon)
                .build();
    }

    public static PostDto specificForm(Post post) {
        List<PostImageDto> images = post.getPostImages().stream()
                .map(PostImageDto::from)
                .collect(Collectors.toList());

        return new PostDto(
                post.getId(),
                post.getCreatedAt(),
                post.getMainImage(),
                post.getMainComment(),
                post.getAuthorId(),
                29.5,
                "http://openweathermap.org/img/wn/01d@2x.png",
                images,
                new ArrayList<>()
        );
    }

    public static PostDto simpleForm(Post post) {
        return new PostDto(
                post.getId(),
                post.getCreatedAt(),
                post.getMainImage(),
                post.getMainComment(),
                post.getAuthorId(),
                29.5,
                "http://openweathermap.org/img/wn/01d@2x.png",
                null,
                new ArrayList<>()
        );
    }

}
