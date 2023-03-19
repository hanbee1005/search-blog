package com.task.searchblog.search.adapter.out.persistance;

import com.task.searchblog.search.domain.Term;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TermRedisRepository extends CrudRepository<Term, String> {
    List<Term> findTop10ByOrderByCountDesc();
}
