package com.modulecommon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    /**
     * 호출을 제한시킬 unique key
     * eg. ip
     */
    String key() default "";

    /**
     * 호출 제한 시간
     */
    long limitTimeSecond() default 1;

    /**
     * 호출 제한 개수
     */
    long limitCount();
}
