package com.aboutTime.common.exception.common;

import com.aboutTime.common.exception.BaseException;
import com.aboutTime.common.exception.ExceptionCode;

public class DuplicateResourceException extends BaseException {
    public DuplicateResourceException(String duplicatedResource) {
        super(duplicatedResource, ExceptionCode.DUPLICATED_RESOURCE);
    }
}
