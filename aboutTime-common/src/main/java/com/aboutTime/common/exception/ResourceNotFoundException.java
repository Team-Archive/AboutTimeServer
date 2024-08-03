package com.aboutTime.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String message;
    private String errorCode;

    public ResourceNotFoundException(String message) {
        this.message = message;
        this.errorCode = "NOT FOUND RESOURCE";
    }

    public ResourceNotFoundException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
