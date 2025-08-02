package com.aboutTime.dto.post;

import com.aboutTime.domain.user.BaseUser;
import com.aboutTime.entity.post.Post;
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
public class PostSaveRequestDto {

    private String mainImage;
    private String mainComment;
    private List<PostImageDto> postImages;

    public Post toEntity(BaseUser author) {
        return Post.builder()
                .mainImage(mainImage)
                .mainComment(mainComment)
                .author(author)
                .build();
    }

}
