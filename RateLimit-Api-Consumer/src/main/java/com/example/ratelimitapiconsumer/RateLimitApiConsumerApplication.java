package com.example.ratelimitapiconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class RateLimitApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateLimitApiConsumerApplication.class, args);
    }

}
