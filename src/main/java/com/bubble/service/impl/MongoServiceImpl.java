package com.bubble.service.impl;

import com.bubble.dao.mongo.TrainStationDao;
import com.bubble.domain.entity.station.TrainStationEntity;
import com.bubble.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库操作服务实现
 *
 * @author wugang
 * date: 2019-04-01 18:07
 **/
@Service
public class MongoServiceImpl implements MongoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoServiceImpl.class);

    private final TrainStationDao trainStationDao;

    @Autowired
    public MongoServiceImpl(TrainStationDao trainStationDao) {
        this.trainStationDao = trainStationDao;
    }

    @Override
    public List<TrainStationEntity> queryTrainStation() {
        List<TrainStationEntity> stationList = trainStationDao.queryAll();
        LOGGER.info("query train station count: {}", stationList.size());
        return stationList;
    }

}
