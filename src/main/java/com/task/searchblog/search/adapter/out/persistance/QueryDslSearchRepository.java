package com.task.searchblog.search.adapter.out.persistance;

import com.task.searchblog.search.domain.KeywordTopTen;

import java.util.List;

public interface QueryDslSearchRepository {
    List<KeywordTopTen> searchTopTenKeyword(int limit);
}
