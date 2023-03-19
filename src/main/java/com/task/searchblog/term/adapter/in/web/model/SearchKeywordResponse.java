package com.task.searchblog.term.adapter.in.web.model;

import com.task.searchblog.term.domain.KeywordTopTen;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchKeywordResponse {
    private int total;
    private List<KeywordTopTen> keywords;

    @Builder
    private SearchKeywordResponse(int total, List<KeywordTopTen> keywords) {
        this.total = total;
        this.keywords = keywords;
    }

    public static SearchKeywordResponse of(List<KeywordTopTen> keywords) {
        return SearchKeywordResponse.builder()
                .total(keywords.size())
                .keywords(keywords)
                .build();
    }
}
