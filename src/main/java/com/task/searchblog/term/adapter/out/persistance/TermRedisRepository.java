package com.task.searchblog.term.adapter.out.persistance;

import com.task.searchblog.term.domain.Term;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TermRedisRepository extends CrudRepository<Term, String> {
    List<Term> findTop10ByOrderByCountDesc();
}
