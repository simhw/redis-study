package com.moduleinfra.support;

import com.modulecommon.annotation.RateLimit;
import com.modulecommon.support.RateLimiter;
import com.moduleinfra.exception.SystemErrorType;
import com.moduleinfra.exception.SystemException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.*;

import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("RedisRateLimiter")
@Slf4j
@RequiredArgsConstructor
public class RedisRateLimiter implements RateLimiter {
    private final RedissonClient redissonClient;

    private final String RATE_LIMIT_LUA_SCRIPT =
            "local key = KEYS[1];" +
            "local limit_count = tonumber(ARGV[1]);" +
            "local limit_time = tonumber(ARGV[2]);" +
            "local current = tonumber(redis.call('GET', key) or '0');" +    // 기존 요청 횟수 조회 및 초기화

            "if current + 1 > limit_count then " +   // 요청 횟수를 초과한 경우
                "return false;" +
            "else " +
                "redis.call('INCRBY', key, '1');" +     // 요청 횟수 +1 증가
                "redis.call('expire', key, limit_time);" +   // 키 만료 시간(ttl) 지정
                "return true;" +
            "end;";

    @Override
    public void validate(Object key, RateLimit rateLimit, ProceedingJoinPoint joinPoint) throws Throwable {
        RScript script = redissonClient.getScript(StringCodec.INSTANCE);
        List<Object> keys = List.of(key);
        Object[] params = new Object[]{rateLimit.limitCount(), rateLimit.limitTimeSecond()};
        boolean allowed = script.eval(RScript.Mode.READ_WRITE, RATE_LIMIT_LUA_SCRIPT, RScript.ReturnType.BOOLEAN, keys, params);

        if (!allowed) {
            throw new SystemException(SystemErrorType.TOO_MANY_REQUEST);
        }
    }

}






























