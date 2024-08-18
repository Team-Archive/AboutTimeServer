package com.aboutTime.api.post;

import com.aboutTime.api.docs.swagger.PostControllerDocs;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.entity.User;
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
    public ResponseEntity<List<PostDto>> postList(User user) {
        return ResponseEntity.ok(postService.getAllPostByUserId(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> postSpecificView(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getOnePostById(id));
    }

//    @PostMapping
//    public ResponseEntity<Object> savePost(@RequestBody PostDto postDto, User user) {
//        postService.save(postDto, user.getId());
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
