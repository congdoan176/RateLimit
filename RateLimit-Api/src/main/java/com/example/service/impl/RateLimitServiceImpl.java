package com.example.service.impl;

import com.example.config.RateConfig;
import com.example.service.RateLimitService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Service
public class RateLimitServiceImpl implements RateLimitService {
    private final String URL = "http://localhost:9000/consumer";
    private Logger logger = LoggerFactory.getLogger(RateLimitServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    RateConfig rateConfig;

    @Override
    public String getData() throws InterruptedException {
        Supplier<String> callConsumer = RateLimiter.decorateSupplier(rateConfig.rateLimitMultiple(), () -> restTemplate.getForObject(URL, String.class));
        return callConsumer.get();
    }
}
