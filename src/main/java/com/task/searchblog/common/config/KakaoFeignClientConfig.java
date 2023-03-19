package com.task.searchblog.common.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
public class KakaoFeignClientConfig {
    @Value("${external.auth.kakao}")
    private String auth;

    private static final String AUTHORIZATION = "Authorization";
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

            // TODO 추가 에러 처리 예정

            return null;
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header(AUTHORIZATION, auth);
        };
    }
}
