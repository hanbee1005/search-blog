package com.task.searchblog.term.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordTopTen {
    private String keyword;
    private Long count;

    public KeywordTopTen(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
