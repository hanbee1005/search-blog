package com.task.searchblog.term.application.service;

import com.task.searchblog.common.exception.BusinessException;
import com.task.searchblog.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TermLockFacade {
    // TODO 별도 설정 파일 등으로 이동 예정
    private final String LOCK_KEY_FOR_INCREASE_COUNT = "increase:count:term:%s";
    private final int LOCK_WAIT_TIME = 10;
    private final int LOCK_LEASE_TIME = 1;

    private final TermCommandService termCommandService;
    private final RedissonClient redissonClient;

    @Async
    public void increaseTermCount(final String term) {
        RLock lock = redissonClient.getLock(String.format(LOCK_KEY_FOR_INCREASE_COUNT, term));
        try {
            boolean available = lock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!available) {
                log.error("Redisson getLock timeout");
                throw new BusinessException(ExceptionType.REDIS_LOCK_TIMEOUT);
            }

            termCommandService.increaseTermCount(term);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
