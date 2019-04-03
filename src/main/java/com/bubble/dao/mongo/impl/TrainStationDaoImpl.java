package com.bubble.dao.mongo.impl;

import com.bubble.dao.mongo.TrainStationDao;
import com.bubble.domain.entity.station.TrainStationEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 国内火车站点信息查询
 *
 * @author wugang
 * date: 2019-04-01 18:12
 **/
@Repository
public class TrainStationDaoImpl implements TrainStationDao {
    private final Class<TrainStationEntity> MONGO_ENTITY_CLASS = TrainStationEntity.class;
    private final MongoTemplate flightMongoTemplate;

    @Autowired
    public TrainStationDaoImpl(@Qualifier(value = "flightMongoTemplate") MongoTemplate flightMongoTemplate) {
        this.flightMongoTemplate = flightMongoTemplate;
    }

    @Override
    public List<TrainStationEntity> queryAll() {
        return Optional.of(flightMongoTemplate.findAll(MONGO_ENTITY_CLASS)).orElse(Lists.newArrayList());
    }

}
