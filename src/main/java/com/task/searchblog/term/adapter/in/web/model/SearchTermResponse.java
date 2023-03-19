package com.task.searchblog.term.adapter.in.web.model;

import com.task.searchblog.term.application.model.TermQuery;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchTermResponse {
    private int total;
    private List<TermQuery> terms;

    @Builder
    private SearchTermResponse(int total, List<TermQuery> terms) {
        this.total = total;
        this.terms = terms;
    }

    public static SearchTermResponse of(List<TermQuery> terms) {
        return SearchTermResponse.builder()
                .total(terms.size())
                .terms(terms)
                .build();
    }
}
