package com.example.controller;

import com.example.config.RateConfig;
import com.example.service.RateLimitService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

@RestController
public class RateLimitController {
    @Autowired
    RateLimitService rateLimitService;
    @Autowired
    RateConfig rateConfig;
    @GetMapping(value = "/getData")
    public ResponseEntity getData(){

        Supplier getDataSupplier = RateLimiter.decorateSupplier(rateConfig.defaultRateLimit(),()-> {
            try {
                return rateLimitService.getData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        try {
            getDataSupplier.get();
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,"Service does not permit further calls");
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/getData1")
    public ResponseEntity getData1(){

        Supplier getDataSupplier = RateLimiter.decorateSupplier(rateConfig.rateLimitMultiple(),()-> {
            try {
                return rateLimitService.getData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        try {
            getDataSupplier.get();
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,"Service does not permit further calls");
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
