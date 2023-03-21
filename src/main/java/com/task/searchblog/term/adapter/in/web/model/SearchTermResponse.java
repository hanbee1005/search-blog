package com.task.searchblog.term.adapter.in.web.model;

import com.task.searchblog.term.application.model.TermDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchTermResponse {
    private int total;
    private List<TermDto> terms;

    @Builder
    private SearchTermResponse(int total, List<TermDto> terms) {
        this.total = total;
        this.terms = terms;
    }

    public static SearchTermResponse of(List<TermDto> terms) {
        return SearchTermResponse.builder()
                .total(terms.size())
                .terms(terms)
                .build();
    }
}
