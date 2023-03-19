package com.task.searchblog.search.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TermLockFacade {
    private final TermCommandService termCommandService;
    private final RedissonClient redissonClient;

    // TODO @Async 또는 추가적으로 문자열 분리 등등 매직넘버 처리 필요
    public void increaseTermCount(final String term) {
        RLock lock = redissonClient.getLock(String.format("increase:count:term:%s", term));
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                log.error("Redisson getLock timeout");
                throw new IllegalArgumentException();
            }

            termCommandService.increaseTermCount(term);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
