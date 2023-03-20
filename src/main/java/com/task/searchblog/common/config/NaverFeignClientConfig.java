package com.task.searchblog.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.searchblog.common.exception.ExternalClientCanNotProceedException;
import com.task.searchblog.common.model.NaverRestApiErrorResponse;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import static com.task.searchblog.common.exception.ExceptionType.*;

@Slf4j
public class NaverFeignClientConfig extends FeignClientConfig {
    @Value("${external.auth.naver.client-id}")
    private String clientId;

    @Value("${external.auth.naver.client-secret}")
    private String clientSecret;

    private static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    @Bean
    public ErrorDecoder decoder() {
        return (methodKey, response) -> {
            HttpStatus responseStatus = HttpStatus.resolve(response.status());

            if (NORMAL_STATUS.contains(responseStatus)) {
                log.error(response.toString());
                return null;
            }

            try {
                NaverRestApiErrorResponse exception = new ObjectMapper().readValue(getBodyString(response), NaverRestApiErrorResponse.class);
                if (MISSING_PARAMETER.getStatus() == responseStatus.value()) {
                    return new ExternalClientCanNotProceedException(MISSING_PARAMETER,
                            String.format(EXCEPTION_MESSAGE_FORMAT, exception.getErrorCode(), exception.getErrorMessage()));
                } else if (NOT_FOUND.getStatus() == responseStatus.value()) {
                    return new ExternalClientCanNotProceedException(NOT_FOUND,
                            String.format(EXCEPTION_MESSAGE_FORMAT, exception.getErrorCode(), exception.getErrorMessage()));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return new ExternalClientCanNotProceedException(UNEXPECTED,
                    String.format(EXCEPTION_MESSAGE_FORMAT,
                            methodKey,
                            getBodyString(response))
            );
        };
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header(X_NAVER_CLIENT_ID, clientId);
            template.header(X_NAVER_CLIENT_SECRET, clientSecret);
        };
    }
}
