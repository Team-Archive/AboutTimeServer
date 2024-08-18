package com.aboutTime.api.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {

    private String message;
    private String code;

    public ExceptionResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
