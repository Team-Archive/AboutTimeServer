package com.aboutTime.api.post;

import com.aboutTime.api.docs.swagger.PostImageControllerDocs;
import com.aboutTime.service.post.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostImageController implements PostImageControllerDocs {

    private final PostImageService imageService;

    @PostMapping(path = "/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file,
                                                 @RequestParam("postId") Long postId,
                                                 @RequestParam(value = "comment", required = false) String comment) {
        var imageUri = imageService.uploadImage(postId, file, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(imageUri);
    }

    @DeleteMapping("/image/remove")
    public ResponseEntity<Void> removeImage(@RequestParam("postImageId") Long postImageId) {
        imageService.deleteImage(postImageId);
        return ResponseEntity.noContent().build();
    }

}
