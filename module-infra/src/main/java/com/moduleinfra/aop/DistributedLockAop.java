package com.moduleinfra.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {
    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(com.moduledomain.command.service.DistributedLockAop)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.moduledomain.command.service.DistributedLockAop distributedLockAop = method.getAnnotation(com.moduledomain.command.service.DistributedLockAop.class);
        String[] keys = distributedLockAop.keys();

        List<RLock> locks = Arrays.stream(keys)
                .map(key -> REDISSON_LOCK_PREFIX + CustomSpringELParser.getDynamicValue(
                        signature.getParameterNames(),
                        joinPoint.getArgs(),
                        key
                ))
                .map(redissonClient::getLock)
                .toList();

        RLock multiLock = redissonClient.getMultiLock(locks.toArray(new RLock[0]));

        try {
            boolean locked = multiLock.tryLock(distributedLockAop.waitTime(), distributedLockAop.leaseTime(), distributedLockAop.timeUnit());
            if (!locked) {
                log.warn("Failed to acquire lock for keys: {}", Arrays.toString(keys));
                return false;  // 락 획득 실패 처리
            }
            return aopForTransaction.proceed(joinPoint);  // 락 획득 후 예약 로직 실행
        } finally {
            try {
                multiLock.unlock();  // 예약이 끝나면 락 해제
            } catch (IllegalMonitorStateException e) {
                log.warn("Lock already unlocked: {}", Arrays.toString(keys));
            }
        }
    }
}
