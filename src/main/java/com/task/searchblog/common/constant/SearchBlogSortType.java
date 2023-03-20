package com.task.searchblog.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchBlogSortType {
    ACCURACY("accuracy", "sim", "정확도순"),
    RECENCY("recency", "date", "최신순");

    private final String kakaoCode;
    private final String naverCode;
    private final String desc;
}
