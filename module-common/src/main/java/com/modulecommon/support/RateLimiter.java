package com.modulecommon.support;

import com.modulecommon.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;

public interface RateLimiter {
    void validate(Object key, RateLimit rateLimit, ProceedingJoinPoint joinPoint) throws Throwable;
}
