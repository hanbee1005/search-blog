package com.task.searchblog.search.adapter.out.persistance;

import com.task.searchblog.search.domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long>, QueryDslSearchRepository {
}
