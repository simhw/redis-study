package com.moduleinfra.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class LocalCacheConfig {
    @Bean
    @Primary
    public CaffeineCacheManager localCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(1000)  // 최대 캐시 항목 크기 초과 시 오래된 항목 삭제
                .expireAfterAccess(1, TimeUnit.MINUTES) // 접근된 후 일정 시간 뒤에 만료
        );
        return cacheManager;
    }
}
