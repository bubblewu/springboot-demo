package com.bubble.dao.mongo;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.bubble.domain.entity.station.TrainStationEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 国内火车站点信息
 *
 * @author wugang
 * date: 2019-04-01 18:12
 **/
public interface TrainStationDao {

    @Cached(name = "trainStation.query", cacheType = CacheType.BOTH, expire = 24, timeUnit = TimeUnit.HOURS)
    List<TrainStationEntity> queryAll();

}
