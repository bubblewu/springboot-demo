package com.bubble.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

/**
 * 测试Demo
 *
 * @author wugang
 * date: 2019-04-01 17:23
 **/
@RestController
@RequestMapping("/test")
@EnableAutoConfiguration
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    /**
     * GET: http://localhost:8080/test/log
     */
    @GetMapping(value = "/log")
    public String testLog() {
        Instant begin = Instant.now();
        String data = "Hello SpringBoot";
        LOGGER.info("data: {}, costs {} ms", data, Duration.between(begin, Instant.now()).toMillis());
        return data;
    }

}
