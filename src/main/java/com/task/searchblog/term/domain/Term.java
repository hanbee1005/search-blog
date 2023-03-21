package com.task.searchblog.term.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash("term")
public class Term {
    @Id
    private String term;
    @Indexed
    private int count;

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
