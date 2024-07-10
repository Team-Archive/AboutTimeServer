package com.aboutTime.service.post;

import com.aboutTime.entity.post.Post;
import com.aboutTime.entity.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 모든 Post 리스트 조회
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    // UserId에 따른 Post 리스트 조회
    public List<Post> getAllPostByUserId(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    // post_id에 따른 Post 단건 조회
    public Post getOnePostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 post가 없습니다."));
    }

    // Post 저장
    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

    // Post 수정


    // Post 삭제
    @Transactional
    public void delete(Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 post가 없습니다."));
        postRepository.delete(post);
    }

}
