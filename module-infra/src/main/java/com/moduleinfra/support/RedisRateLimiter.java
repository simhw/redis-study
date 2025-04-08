package com.moduleinfra.support;

import com.modulecommon.annotation.RateLimit;
import com.modulecommon.support.RateLimiter;
import com.moduleinfra.exception.SystemErrorType;
import com.moduleinfra.exception.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RBucket;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component("RedisRateLimiter")
@Slf4j
@RequiredArgsConstructor
public class RedisRateLimiter implements RateLimiter {
    private final RedissonClient redissonClient;
    private final String BLOCK_KEY = "block:";

    @Override
    public void validate(Object key, RateLimit rateLimit, ProceedingJoinPoint joinPoint) throws Throwable {
        // 차단 확인
        RBucket<Boolean> block = redissonClient.getBucket(BLOCK_KEY + key.toString());
        if (block.isExists()) {
            throw new SystemException(SystemErrorType.TOO_MANY_REQUEST, rateLimit.count());
        }

        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key.toString());
        Duration interval = Duration.of(rateLimit.ttl(), rateLimit.timeUnit().toChronoUnit());

        // init (이미 설정돼있으면 무시됨)
        rateLimiter.trySetRate(RateType.OVERALL, rateLimit.count(), interval);
        boolean allowed = rateLimiter.tryAcquire(1);

        if (!allowed) {
            // 1시간 차단 설정
            block.set(true, 1, TimeUnit.HOURS);
            throw new SystemException(SystemErrorType.REQUEST_BLOCKED, rateLimit.count());
        }
        try {
            joinPoint.proceed();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
