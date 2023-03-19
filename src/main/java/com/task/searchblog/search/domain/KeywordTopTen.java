package com.task.searchblog.search.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordTopTen {
    private String keyword;
    private Long count;

    @QueryProjection
    public KeywordTopTen(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
