package com.task.searchblog.search.application.service;

import com.task.searchblog.search.adapter.out.persistance.TermRedisRepository;
import com.task.searchblog.search.domain.Term;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TermCommandService {
    private final TermRedisRepository termRedisRepository;

    public void increaseTermCount(String key) {
        termRedisRepository.findById(key)
                .ifPresentOrElse(term -> {
                    term.updateCount();
                    termRedisRepository.save(term);
                }, () -> termRedisRepository.save(Term.create(key)));
    }
}
