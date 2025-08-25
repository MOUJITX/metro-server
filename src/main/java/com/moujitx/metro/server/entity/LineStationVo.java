package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName line_station_vo
 */
@TableName(value = "line_station_vo")
@Data
public class LineStationVo {
    private String bindUuid;

    private String cityName;

    private String lineName;

    private String stationName;

    private Double stationLongitude;

    private Double stationLatitude;

    private Integer sort;

    private String cityCode;

    private String lineCode;
}