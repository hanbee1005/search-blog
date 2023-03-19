package com.task.searchblog.common.exception.handler;

import com.task.searchblog.common.exception.BusinessException;
import com.task.searchblog.common.exception.ExceptionType;
import com.task.searchblog.common.exception.ExternalClientCanNotProceedException;
import com.task.searchblog.common.model.CommonErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.task.searchblog.common.constant.ValidatorCodeConstants.*;

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

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<CommonErrorResponse> handleException(final Exception e) {
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            String message = handleBindException(bindException);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(CommonErrorResponse.of(new BusinessException(ExceptionType.INVALID_REQUEST, message)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonErrorResponse.of(new BusinessException(ExceptionType.UNEXPECTED, e.getMessage())));
        }
    }

    private String handleBindException(final BindException exception) {
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();

        for (FieldError error : fieldErrorList) {
            if (NOT_EMPTY.equals(error.getCode()) || NOT_BLANK.equals(error.getCode())) {
                return error.getField() + "은(는) " + error.getDefaultMessage();
            } else if (MIN.equals(error.getCode()) || MAX.equals(error.getCode())) {
                return error.getField() + "은(는)" + error.getDefaultMessage();
            } else if (TYPE_MISMATCH.equals(error.getCode())) {
                return error.getRejectedValue() + "은(는) " + error.getField() + " 타입에 존재하지 않는 값입니다.";
            }
        }

        return Strings.EMPTY;
    }
}
