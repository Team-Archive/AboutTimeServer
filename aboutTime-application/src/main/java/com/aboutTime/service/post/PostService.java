package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.entity.User;
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

    public List<PostDto> getAllPostByUserId(User user) {
        return postRepository.findAllByAuthorId((user.getId())).stream()
                .map(PostDto::from)
                .toList();
    }

    public PostDto getOnePostById(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 post가 없습니다."));

        return PostDto.from(post);
    }

//    @Transactional
//    public void save(PostDto postDto, Long authorId) {
//        var user = userRepository.findById(authorId)
//                        .orElseThrow(() -> new ResourceNotFoundException("해당하는 유저가 없습니다."));
//        var post = postRepository.save(postDto.toEntity(user));
//
//        Objects.requireNonNull(postDto.getPostImages()).stream()
//                .map(archiveImageDto -> archiveImageDto.toEntity(post))
//                .forEach(post::addImage);
//    }

    @Transactional
    public void delete(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 post가 없습니다."));
        postRepository.delete(post);
    }

}
