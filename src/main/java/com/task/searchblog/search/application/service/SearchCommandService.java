package com.task.searchblog.search.application.service;

import com.task.searchblog.search.adapter.out.persistance.SearchRepository;
import com.task.searchblog.search.domain.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SearchCommandService {
    private final SearchRepository searchRepository;

    @Async
    public void addSearchKeyword(Long memberId, String keyword) {
        searchRepository.save(Search.create(memberId, keyword));
    }
}
