package com.task.searchblog.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.searchblog.common.exception.ExternalClientCanNotProceedException;
import com.task.searchblog.common.exception.KakaoClientException;
import com.task.searchblog.common.model.KakaoRestApiErrorResponse;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import static com.task.searchblog.common.exception.ExceptionType.*;

@Slf4j
public class KakaoFeignClientConfig extends FeignClientConfig {
    @Value("${external.auth.kakao}")
    private String auth;

    private static final String AUTHORIZATION = "Authorization";

    @Bean
    public ErrorDecoder decoder() {
        return (methodKey, response) -> {
            HttpStatus responseStatus = HttpStatus.resolve(response.status());

            if (NORMAL_STATUS.contains(responseStatus)) {
                log.error(response.toString());
                return null;
            }

            try {
                KakaoRestApiErrorResponse exception = new ObjectMapper().readValue(getBodyString(response), KakaoRestApiErrorResponse.class);
                if (MISSING_PARAMETER.getStatus() == responseStatus.value()) {
                    return new ExternalClientCanNotProceedException(MISSING_PARAMETER, exception.getMessage());
                } else if(INVALID_AUTH.getStatus() == responseStatus.value()) {
                    return new ExternalClientCanNotProceedException(INVALID_AUTH, exception.getMessage());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            throw new KakaoClientException();
        };
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header(AUTHORIZATION, auth);
        };
    }
}
