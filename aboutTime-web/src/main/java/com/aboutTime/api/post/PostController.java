package com.aboutTime.api.post;

import com.aboutTime.api.docs.swagger.PostControllerDocs;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.dto.post.PostSaveRequestDto;
import com.aboutTime.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> postList(@RequestParam("authorId") Long authorId) {
        return ResponseEntity.ok(postService.getAllPostByUserId(authorId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> postSpecificView(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getOnePostById(postId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @PostMapping
    public ResponseEntity<Object> savePost(@RequestBody PostSaveRequestDto requestDto, @RequestParam("authorId") Long authorId) {
        postService.save(requestDto, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
