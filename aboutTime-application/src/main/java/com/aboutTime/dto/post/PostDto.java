package com.aboutTime.dto.post;

import com.aboutTime.entity.User;
import com.aboutTime.entity.post.Post;
import com.aboutTime.entity.post.PostImage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PostDto {
    private Long postId;
    private Long authorId;

    private List<PostImageDto> postImages;

    public static PostDto from(Post post) {
        var postImages = post.getPostImages().stream()
                .map(PostImageDto::from)
                .toList();

        return PostDto.builder()
                .postId(post.getId())
                .authorId(post.getAuthor().getId())
                .postImages(postImages)
                .build();
    }

    public Post toEntity(User author) {
        return Post.builder()
                .author(author)
                .build();
    }

}
