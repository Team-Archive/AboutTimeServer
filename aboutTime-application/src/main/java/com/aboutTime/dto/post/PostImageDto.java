package com.aboutTime.dto.post;

import com.aboutTime.entity.post.Post;
import com.aboutTime.entity.post.PostImage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PostImageDto {

    private String url;
    private String comment;

    public static PostImageDto from(PostImage postImage) {
        return PostImageDto.builder()
                .url(postImage.getUrl())
                .comment(postImage.getComment())
                .build();
    }

    public PostImage toEntity(Post post) {
        return new PostImage(url, comment, post);

    }
}
