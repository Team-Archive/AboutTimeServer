package com.aboutTime.api.user;

import com.aboutTime.api.docs.swagger.UserControllerDocs;
import com.aboutTime.dto.user.BaseUserDto;
import com.aboutTime.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "user-controller", description = "회원정보 관련 API 목록")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<BaseUserDto> findUser(@Validated @RequestParam long idx) {
        return ResponseEntity.ok(userService.findUserById(idx));
    }
}
