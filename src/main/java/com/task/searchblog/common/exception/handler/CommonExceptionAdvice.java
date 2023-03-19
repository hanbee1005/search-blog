package com.task.searchblog.common.exception.handler;

import com.task.searchblog.common.exception.BusinessException;
import com.task.searchblog.common.exception.ExternalClientCanNotProceedException;
import com.task.searchblog.common.model.CommonErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionAdvice {
    @ExceptionHandler(value = {ExternalClientCanNotProceedException.class})
    protected ResponseEntity<CommonErrorResponse> handleException(ExternalClientCanNotProceedException exception) {
        log.error("External Client CanNotProceed Exception occurred.");
        return ResponseEntity.status(exception.getStatus()).body(CommonErrorResponse.of(exception));
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<CommonErrorResponse> handleException(BusinessException exception) {
        log.error("Business Exception occurred.");
        return ResponseEntity.status(exception.getStatus()).body(CommonErrorResponse.of(exception));
    }
}
