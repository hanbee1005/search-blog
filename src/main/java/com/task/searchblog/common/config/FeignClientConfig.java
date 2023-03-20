package com.task.searchblog.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.searchblog.common.exception.BusinessException;
import com.task.searchblog.common.exception.ExternalClientCanNotProceedException;
import com.task.searchblog.common.model.KakaoRestApiErrorResponse;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.task.searchblog.common.exception.ExceptionType.MISSING_PARAMETER;
import static com.task.searchblog.common.exception.ExceptionType.UNEXPECTED;

@Slf4j
public class FeignClientConfig {
    private static final String EXCEPTION_MESSAGE_FORMAT = "[%s] : %s";
    private static final int PERIOD = 1000;
    private static final int MAX_PERIOD = 2000;
    private static final int MAX_ATTEMPTS = 1;

    private static final List<HttpStatus> NORMAL_STATUS = List.of(HttpStatus.OK,
            HttpStatus.CREATED,
            HttpStatus.ACCEPTED,
            HttpStatus.MOVED_PERMANENTLY);

    @Bean
    public Retryer retryer() { return new Retryer.Default(PERIOD, MAX_PERIOD, MAX_ATTEMPTS); }

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
    public Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }

    private String getBodyString(Response response) {
        if (null == response || null == response.body()) {
            return Strings.EMPTY;
        }

        try {
            return IOUtils.toString(response.body().asReader(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(UNEXPECTED, e.getMessage());
        }
    }
}
