package com.task.searchblog.search.application.service;

import com.task.searchblog.search.adapter.out.persistance.TermRedisRepository;
import com.task.searchblog.search.domain.Term;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class TermCommandServiceTest {
    @Autowired TermLockFacade termLockFacade;
    @Autowired TermRedisRepository termRedisRepository;

    @Test
    @DisplayName("동시에 100명이 같은 키워드 조회")
    public void addTerm() throws InterruptedException {
        // given
        String term = "카카오뱅크";
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    termLockFacade.increaseTermCount(term);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        // then
        countDownLatch.await();
        Term findTerm = termRedisRepository.findById(term).orElseThrow();
        Assertions.assertThat(findTerm.getCount()).isEqualTo(100);
    }
}