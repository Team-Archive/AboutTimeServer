package com.aboutTime.entity.post;

import com.aboutTime.domain.user.BaseUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private BaseUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;

    @Column(name = "count")
    private int count;

    @Builder
    public Reaction(Long id, Post post, BaseUser user, Emoji emoji, int count) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.emoji = emoji;
        this.count = count;
    }

    public void increaseCount() {
        this.count++;
    }

}
