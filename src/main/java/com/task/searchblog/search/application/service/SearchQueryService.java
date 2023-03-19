package com.task.searchblog.search.application.service;

import com.task.searchblog.search.adapter.out.persistance.SearchRepository;
import com.task.searchblog.search.domain.KeywordTopTen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchQueryService {
    private final SearchRepository searchRepository;

    public List<KeywordTopTen> searchTopKeyword(int limit) {
        return searchRepository.searchTopKeyword(limit);
    }
}
