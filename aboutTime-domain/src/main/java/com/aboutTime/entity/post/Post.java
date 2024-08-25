package com.aboutTime.entity.post;

import com.aboutTime.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
@SQLDelete(sql = "UPDATE post SET is_deleted = true WHERE post_id=?")
@SQLRestriction("is_deleted = false")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "main_comment")
    private String mainComment;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "author_id", nullable = false)
//    private User author;
    // 테스트를 위해 authorId=1인 유저만 사용
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "current_temperature")
    private Double currentTemperature;

    @Column(name = "weather_icon")
    private String weatherIcon;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final List<Reaction> reactions = new ArrayList<>();

    @Builder
    public Post(Long id, String mainImage, String mainComment, Long authorId, Double currentTemperature, String weatherIcon) {
        this.id = id;
        this.mainImage = mainImage;
        this.mainComment = mainComment;
        this.authorId = authorId;
        this.currentTemperature = currentTemperature;
        this.weatherIcon = weatherIcon;
    }

    public void addImage(PostImage postImage) {
        this.postImages.add(postImage);
    }

//    public void addReaction(Reaction reaction) {
//        this.reactions.add(reaction);
//    }

}
