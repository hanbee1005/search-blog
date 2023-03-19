package com.task.searchblog.search.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("term")
public class Term {
    @Id
    private String term;
    private int count;

    @Builder
    private Term(String term, int count) {
        this.term = term;
        this.count = count;
    }

    public static Term create(String term) {
        return new Term(term, 1);
    }

    public void updateCount() {
        count += 1;
    }
}
