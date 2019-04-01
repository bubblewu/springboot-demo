package com.bubble.service;

import com.bubble.domain.entity.station.TrainStationEntity;

import java.util.List;

/**
 * 数据库操作服务
 *
 * @author wugang
 * date: 2019-04-01 18:08
 **/
public interface MongoService {

    List<TrainStationEntity> queryTrainStation();

}
