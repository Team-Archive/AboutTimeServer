package com.aboutTime.dto.post;

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

    public Post toEntity(Long authorId, double temperature, String weatherIcon) {
        return Post.builder()
                .mainImage(mainImage)
                .mainComment(mainComment)
                .authorId(authorId)
                .currentTemperature(temperature)
                .weatherIcon(weatherIcon)
                .build();
    }

}
