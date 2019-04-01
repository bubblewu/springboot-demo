package com.bubble.dao;

import com.bubble.domain.entity.station.TrainStationEntity;

import java.util.List;

/**
 * 国内火车站点信息
 *
 * @author wugang
 * date: 2019-04-01 18:12
 **/
public interface TrainStationDao {

    List<TrainStationEntity> queryAll();

}
