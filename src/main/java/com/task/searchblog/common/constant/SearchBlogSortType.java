package com.task.searchblog.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchBlogSortType {
    accuracy("정확도순"),
    recency("최신순");

    private final String desc;
}
