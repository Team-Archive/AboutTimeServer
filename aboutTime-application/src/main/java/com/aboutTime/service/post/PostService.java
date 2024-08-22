package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.dto.post.PostSaveRequestDto;
import com.aboutTime.entity.User;
import com.aboutTime.entity.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getAllPostByUserId(Long userId) {
//        return postRepository.findAllByAuthorId((user.getId())).stream()
//                .map(PostDto::from)
//                .toList();
        return postRepository.findAllByAuthorId(userId).stream()
                .map(PostDto::simpleForm)
                .toList();
    }

    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream()
                .map(PostDto::simpleForm)
                .toList();
    }

    public PostDto getOnePostById(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 post가 없습니다."));

        return PostDto.specificForm(post);
    }

    @Transactional
    public void save(PostSaveRequestDto postRequestDto, Long authorId) {
//        var user = userRepository.findById(authorId)
//                        .orElseThrow(() -> new ResourceNotFoundException("해당하는 유저가 없습니다."));
        var post = postRepository.save(postRequestDto.toEntity(authorId));

        Objects.requireNonNull(postRequestDto.getPostImages()).stream()
                .map(archiveImageDto -> archiveImageDto.toEntity(post))
                .forEach(post::addImage);
    }

    @Transactional
    public void delete(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 post가 없습니다."));
        postRepository.delete(post);
    }

}
