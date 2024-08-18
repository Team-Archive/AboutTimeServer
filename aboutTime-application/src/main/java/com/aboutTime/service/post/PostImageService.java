package com.aboutTime.service.post;

import com.aboutTime.common.exception.ResourceNotFoundException;
import com.aboutTime.entity.post.PostImage;
import com.aboutTime.entity.post.PostImageRepository;
import com.aboutTime.entity.post.PostRepository;
import com.aboutTime.infra.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostImageService {

    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    public String uploadImage(Long postId, MultipartFile file, String comment) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        var imageUri = s3Service.upload(file);
        var postImage = new PostImage(imageUri, comment, post);
        postImageRepository.save(postImage);

        return imageUri;
    }


    public void deleteImage(Long postImageId) {
        var postImage = postImageRepository.findById(postImageId)
                .orElseThrow(() -> new ResourceNotFoundException("PostImage not found with id: " + postImageId));

        s3Service.remove(postImage.getUrl());
        postImageRepository.delete(postImage);
    }

}
