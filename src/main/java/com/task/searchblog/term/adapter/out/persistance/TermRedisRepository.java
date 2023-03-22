package com.task.searchblog.term.adapter.out.persistance;

import com.task.searchblog.term.domain.Term;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TermRedisRepository extends CrudRepository<Term, String> {
    // findTop10ByOrderByCountDesc(); 로 결과가 정확하지 않아서 모두 조회함 (추가 확인 필요 ㅠ)
    List<Term> findByOrderByCountDesc();
}
