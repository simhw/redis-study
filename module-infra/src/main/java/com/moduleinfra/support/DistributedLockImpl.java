package com.moduleinfra.support;

import com.modulecommon.support.IDistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributedLockImpl implements IDistributedLock {
    private final RedissonClient redissonClient;
    private final String REDISSON_LOCK_PREFIX = "LOCK:";

    @Override
    public <T> T lockAndExecute(String[] keys, long waitTime, long leaseTime, TimeUnit unit, Supplier<T> function) {
        List<RLock> locks = Arrays.stream(keys)
                .map(key -> REDISSON_LOCK_PREFIX + key)
                .map(redissonClient::getLock)
                .toList();
        RLock multiLock = redissonClient.getMultiLock(locks.toArray(new RLock[0]));
        try {
            boolean locked = multiLock.tryLock(waitTime, leaseTime, unit);
            if (!locked) {
                throw new IllegalStateException("Lock 획득 실패");
            }
            return function.get();  // 람다로 전달된 비즈니스 로직 실행
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                multiLock.unlock();  // 예약이 끝나면 락 해제
            } catch (IllegalMonitorStateException e) {
                log.warn("Lock already unlocked: {}", Arrays.toString(keys));
            }
        }
    }
}
