package com.task.searchblog.search.adapter.out.persistance;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.task.searchblog.search.domain.KeywordTopTen;
import com.task.searchblog.search.domain.QKeywordTopTen;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.task.searchblog.search.domain.QSearch.search;

@RequiredArgsConstructor
public class QueryDslSearchRepositoryImpl implements QueryDslSearchRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<KeywordTopTen> searchTopKeyword(int limit) {
        NumberPath<Long> count = Expressions.numberPath(Long.class, "cnt");
        return jpaQueryFactory
                .select(new QKeywordTopTen(search.keyword, search.id.count().as("cnt")))
                .from(search)
                .groupBy(search.keyword)
                .orderBy(count.desc())
                .limit(limit)
                .fetch();
    }
}
