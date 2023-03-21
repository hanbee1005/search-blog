package com.task.searchblog.term.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TermDto {
    private String term;
    private Long count;

    public TermDto(String term, Long count) {
        this.term = term;
        this.count = count;
    }
}
