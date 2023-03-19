package com.task.searchblog.common.model;

import lombok.Getter;

@Getter
public class KakaoRestApiErrorResponse {
    private String errorType;
    private String message;
}
