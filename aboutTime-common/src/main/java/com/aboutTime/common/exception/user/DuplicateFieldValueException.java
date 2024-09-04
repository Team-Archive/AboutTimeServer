package com.aboutTime.common.exception.user;

import com.aboutTime.common.exception.BaseException;
import com.aboutTime.common.exception.ExceptionCode;

public class DuplicateFieldValueException extends BaseException {
    public DuplicateFieldValueException(String duplicatedField, String duplicatedValue) {
        super("'" + duplicatedValue + "'은 중복된 " + duplicatedField + " 입니다. 다른 " + duplicatedField + " 을 사용해주세요",
                ExceptionCode.DUPLICATED_FIELD_VALUE);
    }
}
