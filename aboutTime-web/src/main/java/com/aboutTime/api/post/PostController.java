package com.aboutTime.api.post;

import com.aboutTime.api.docs.swagger.PostControllerDocs;
import com.aboutTime.dto.post.EmojiReactionRequest;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.dto.post.PostSaveRequestDto;
import com.aboutTime.dto.post.ReactionDto;
import com.aboutTime.service.post.PostService;
import com.aboutTime.service.post.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {

    private final PostService postService;
    private final ReactionService reactionService;

    @GetMapping
    public ResponseEntity<List<PostDto>> postList(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(postService.getAllPostByUserId(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> postSpecificView(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getOnePostById(postId));
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

    /**
     * 게시글의 이모지 반응 추가
     */
    @PostMapping("/{postId}/reactions")
    public ResponseEntity<ReactionDto> reactToPost(
            @PathVariable Long postId,
            @RequestBody EmojiReactionRequest requestDto) {
        return ResponseEntity.ok(reactionService.reactToPost(postId, requestDto));
    }

    @GetMapping("/{postId}/emoji-statistics")
    public ResponseEntity<Map<String, Integer>> getEmojiStatistics(@PathVariable Long postId) {
        return ResponseEntity.ok(reactionService.getEmojiStatistics(postId));
    }

    /**
     * 특정 이모지에 반응한 사용자 목록 조회
     */
    @GetMapping("/{postId}/reactions/{emojiType}")
    public ResponseEntity<Map<Long, Integer>> getUsersByEmoji(
            @PathVariable Long postId,
            @PathVariable String emojiType) {

        // 특정 이모지에 반응한 사용자별 카운트 조회
        Map<Long, Integer> userReactions = reactionService.getUserReactionsByEmoji(postId, emojiType);
        return ResponseEntity.ok(userReactions);
    }

}
