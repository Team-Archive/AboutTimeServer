package com.aboutTime.entity.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.author.idx = :authorId")
    List<Post> findAllByAuthorId(Long authorId);

}
