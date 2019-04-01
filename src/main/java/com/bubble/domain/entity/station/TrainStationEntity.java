package com.bubble.domain.entity.station;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 国内火车站信息
 *
 * @author wugang
 * date: 2019-04-01 18:02
 **/
@Document(value = "railwayStation")
public class TrainStationEntity implements Serializable {
    private static final long serialVersionUID = 640717872831655367L;

    @Field(value = "_id")
    private String stationCode;
    @Field(value = "cnname")
    private String stationName;
    @Field(value = "citycn")
    private String cityName;
    @Field(value = "province")
    private String province;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "TrainStationEntity{" +
                "stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
