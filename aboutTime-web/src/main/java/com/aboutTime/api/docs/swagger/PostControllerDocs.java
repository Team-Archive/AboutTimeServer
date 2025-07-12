package com.aboutTime.api.docs.swagger;

import com.aboutTime.dto.post.EmojiReactionRequest;
import com.aboutTime.dto.post.PostDto;
import com.aboutTime.dto.post.PostSaveRequestDto;
import com.aboutTime.dto.post.ReactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PostControllerDocs {

    @Operation(summary = "게시글 리스트 조회", description = "특정 사용자의 모든 게시글을 조회합니다.")
    ResponseEntity<List<PostDto>> postList(@Parameter(name = "authorId") Long authorId);

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID를 통해 게시글을 상세 조회합니다.")
    ResponseEntity<PostDto> postSpecificView(@Parameter(name = "postId", description = "조회할 게시글의 ID") Long postId);

    @Operation(summary = "게시글 전체 조회", description = "[테스트용] DB에 저장되어 있는 모든 게시글을 조회합니다.")
    ResponseEntity<List<PostDto>> getAllPost();

    @Operation(summary = "게시글 생성")
    ResponseEntity<Void> savePost(@Parameter PostSaveRequestDto requestDto, @Parameter(name = "authorId") Long authorId);

    @Operation(summary = "게시글 삭제")
    ResponseEntity<Void> deletePost(@Parameter(name = "id", description = "삭제할 게시글의 ID") Long id);

    @Operation(summary = "게시글 반응 생성", description = "게시글에 반응한 기록을 추가한다.")
    ResponseEntity<ReactionDto> reactToPost(@Parameter(name = "postId", description = "조회할 게시글의 ID") Long postId,
                                            @Parameter(name = "requestDto", description = "반응 정보")EmojiReactionRequest requestDto);

    @Operation(summary = "게시글 반응 조회", description = "게시글에 기록된 반응을 모두 조회한다.")
    ResponseEntity<Map<String, Integer>> getEmojiStatistics(@Parameter(name = "postId", description = "조회할 게시글의 ID") Long postId);

    @Operation(summary = "특정 이모지에 반응한 사용자 목록", description = "게시글에 기록된 특정 이모지를 조회한다.")
    ResponseEntity<Map<Long, Integer>> getUsersByEmoji(@Parameter(name = "postId") Long postId,
                                                         @Parameter(name = "emojiType") String emojiType);

}
