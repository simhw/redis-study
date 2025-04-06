package com.moduleinfra.aop;

import com.modulecommon.annotation.RateLimit;
import com.modulecommon.support.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("LocalRateLimiter")
@Slf4j
@RequiredArgsConstructor
public class LocalRateLimiter implements RateLimiter {

    @Override
    public void validate(Object key, RateLimit rateLimit, ProceedingJoinPoint joinPoint) throws Throwable {
        return;
    }
}
