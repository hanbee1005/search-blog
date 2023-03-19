package com.task.searchblog.common.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ExceptionType {
    // standard
    BAD_REQUEST("301", HttpStatus.BAD_REQUEST.value(), "exception.standard.badRequest"),
    NOT_FOUND("304", HttpStatus.NOT_FOUND.value(), "exception.standard.notFound"),
    UNEXPECTED("305", HttpStatus.INTERNAL_SERVER_ERROR.value(), "exception.standard.unexpected"),

    // business
    INVALID_REQUEST("401", HttpStatus.BAD_REQUEST.value(), "exception.common.validation.request"),
    MISSING_PARAMETER("402", HttpStatus.BAD_REQUEST.value(), "exception.external.missing.parameter"),
    REDIS_LOCK_TIMEOUT("501", HttpStatus.INTERNAL_SERVER_ERROR.value(), "exception.timeout.redis")
    ;

    private final String code;
    private final int status;
    private final String message;

    public String getCode() {
        return status + code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
