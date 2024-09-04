package com.aboutTime.common.exception;

import java.util.Optional;

public class BaseException extends RuntimeException {
    private final ExceptionCode exceptionCode;
    private String additionalMessage;

    public BaseException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BaseException(String additionalMessage, ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage() + " : " + additionalMessage);
        this.exceptionCode = exceptionCode;
        this.additionalMessage = additionalMessage;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public Optional<String> getAdditionalMessage() {
        return Optional.ofNullable(additionalMessage);
    }
}
