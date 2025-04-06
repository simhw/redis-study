package com.modulecommon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 분당 호출 제한시킬 unique key
     * eg. ip
     */
    String key() default "";

    /**
     * 호출 제한 시간
     */
    long ttl() default 1;

    TimeUnit timeUnit() default TimeUnit.MINUTES;

    /**
     * 호출 제한 카운트
     */
    long count();
}
