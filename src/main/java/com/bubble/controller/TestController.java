package com.bubble.controller;

import com.bubble.domain.entity.trip.TripEntity;
import com.bubble.domain.exception.ValidationFailedException;
import com.bubble.service.CheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

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

    private final CheckService checkService;

    @Autowired
    public TestController(CheckService checkService) {
        this.checkService = checkService;
    }

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

    /**
     * - POST:
     * http://localhost:8080/test/valid
     * - Body:
     * {
     * "userId":"123",
     * "depCode":"wux",
     * "arrCode":"pek",
     * "arrTime":"2019-04-30 08:30:00",
     * "depTime":"2019-04-30 18:30:00"
     * }
     */
    @PostMapping(value = "/valid")
    @ResponseStatus(HttpStatus.CREATED)
    public String testPostParamValid(@RequestBody @Valid TripEntity trip, Errors bodyErrors) {
        checkService.checkErrors(Optional.ofNullable(bodyErrors));
        LOGGER.info("trip: {}", trip.toString());
        return trip.toString();
    }

}
