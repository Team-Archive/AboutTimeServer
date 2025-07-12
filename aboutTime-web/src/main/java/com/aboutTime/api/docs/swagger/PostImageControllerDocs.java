package com.aboutTime.api.docs.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface PostImageControllerDocs {

    @Operation(summary = "이미지 업로드")
    ResponseEntity<String> uploadImage(
            @Parameter(name = "image") MultipartFile file,
            @Parameter(name = "postId", description = "게시글 아이디") Long postId,
            @Parameter(name = "comment", description = "사진 설명") String comment);

    @Operation(summary = "이미지 삭제")
    ResponseEntity<Void> removeImage(@Parameter(name = "postImageId") Long postImageId);

}
