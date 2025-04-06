package com.modulecommon.annotation;

import com.modulecommon.support.CustomSpringELParser;
import com.modulecommon.support.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RateLimitAspect {
    private final RateLimiter rateLimiter;

    public RateLimitAspect(@Qualifier("RedisRateLimiter") RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    /*
     * 런타임 시점에 타겟으로 설정한 메서드가 실행되기 직전에 실행되는 코드입니다.
     * @Around 어노테이션에는 타겟으로 설정 가능한 범위를 정의합니다.
     * (https://www.baeldung.com/spring-aop-pointcut-tutorial)
     */
    @Around("@annotation(RateLimit)")
    public void interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        // rateLimit 커스텀 어노테이션을 설정한 타겟 메서드만 분당 호출 제한을 체크
        rateLimiter.validate(
                CustomSpringELParser.getDynamicValue(
                        signature.getParameterNames(),
                        joinPoint.getArgs(),
                        rateLimit.key()
                ),
                rateLimit,
                joinPoint
        );
    }

}
