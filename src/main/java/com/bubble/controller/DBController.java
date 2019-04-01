package com.bubble.controller;

import com.bubble.domain.entity.station.TrainStationEntity;
import com.bubble.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * 数据库相关操作
 *
 * @author wugang
 * date: 2019-04-01 18:06
 **/
@RestController
@RequestMapping("/test/db")
@EnableAutoConfiguration
public class DBController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBController.class);

    private final MongoService mongoService;

    @Autowired
    public DBController(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    /**
     * GET: http://localhost:8080/test/db/mongo/train
     */
    @GetMapping(value = "/mongo/train")
    public String queryMongo() {
        Instant begin = Instant.now();
        List<TrainStationEntity> trainStationList = mongoService.queryTrainStation();
        LOGGER.info("query train station count: {}, costs {} ms", trainStationList.size(), Duration.between(begin, Instant.now()).toMillis());
        return "query train station count: " + trainStationList.size();
    }

}
