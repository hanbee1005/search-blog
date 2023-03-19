package com.task.searchblog.common.exception;

public class ExternalClientCanNotProceedException extends CanNotProceedException {
    private ExceptionType exceptionType;
    private String message;

    public ExternalClientCanNotProceedException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public ExternalClientCanNotProceedException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = exceptionType.getMessage();
    }

    @Override
    public int getStatus() {
        return exceptionType.getStatus();
    }

    @Override
    public String getErrorCode() {
        return exceptionType.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
