package com.task.searchblog.term.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("term")
public class Term {
    @Id
    private String term;
    private int count;

    private Term(String term) {
        this.term = term;
    }

    public static Term create(String term) {
        return new Term(term);
    }

    public void updateCount() {
        count += 1;
    }
}
