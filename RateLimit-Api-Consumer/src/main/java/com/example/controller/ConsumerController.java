package com.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private Logger logger = LogManager.getLogger(ConsumerController.class);

    @GetMapping(value = "/consumer")
    public String consumerApi() throws InterruptedException {
        logger.info("Call consumer success");
        return Strings.EMPTY;
    }
}
