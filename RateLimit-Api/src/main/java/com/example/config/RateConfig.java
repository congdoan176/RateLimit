package com.example.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
@Configuration
public class RateConfig {
    @Bean
    public RateLimiter defaultRateLimit(){
        RateLimiterConfig configBasic = RateLimiterConfig.custom().
                limitForPeriod(5).
                limitRefreshPeriod(Duration.ofSeconds(1)).
                timeoutDuration(Duration.ofMillis(0)).build();
        RateLimiterRegistry registry = RateLimiterRegistry.of(configBasic);
        RateLimiter rateLimiter = registry.rateLimiter("default",configBasic);
        return rateLimiter;
    }
    @Bean
    public RateLimiter rateLimitMultiple(){
        RateLimiterConfig configMultiple = RateLimiterConfig.custom().
                limitForPeriod(2).
                limitRefreshPeriod(Duration.ofSeconds(1)).
                timeoutDuration(Duration.ofMillis(0)).build();
        RateLimiterRegistry registry = RateLimiterRegistry.of(configMultiple);
        RateLimiter rateLimiter = registry.rateLimiter("default1",configMultiple);
        return rateLimiter;
    }
}
