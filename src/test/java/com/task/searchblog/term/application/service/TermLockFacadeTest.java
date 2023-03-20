package com.task.searchblog.term.application.service;

import com.task.searchblog.term.adapter.out.persistance.TermRedisRepository;
import com.task.searchblog.term.domain.Term;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TermLockFacadeTest {
    @Autowired
    TermLockFacade termLockFacade;
    @Autowired
    TermRedisRepository termRedisRepository;

    @Test
    @DisplayName("동시에 100명이 같은 키워드 조회")
    public void addTerm() throws InterruptedException {
        // given
        int threadCount = 1000;
        String term = "테스트코드";
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
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
        Assertions.assertThat(findTerm.getCount()).isEqualTo(threadCount);
    }
}