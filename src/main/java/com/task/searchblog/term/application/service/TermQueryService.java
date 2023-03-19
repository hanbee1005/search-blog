package com.task.searchblog.term.application.service;

import com.task.searchblog.term.adapter.out.persistance.TermRedisRepository;
import com.task.searchblog.term.domain.KeywordTopTen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermQueryService {
    private final TermRedisRepository termRedisRepository;

    public List<KeywordTopTen> searchTopKeyword(int limit) {
        return termRedisRepository.findTop10ByOrderByCountDesc()
                .stream()
                .map(term -> new KeywordTopTen(term.getTerm(), (long) term.getCount()))
                .collect(Collectors.toList());
    }
}
