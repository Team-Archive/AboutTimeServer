package com.aboutTime.api.docs.swagger;

import com.aboutTime.dto.user.BaseUserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserControllerDocs {
    @Operation(summary = "회원정보 조회", description = "사용자 회원 정보 조회.")
    ResponseEntity<BaseUserDto> findUser(@RequestParam long idx);
}
