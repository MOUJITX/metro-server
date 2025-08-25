package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @TableName station_vo
 */
@TableName(value = "station_vo")
@Data
public class StationVo {
    @TableId(type = IdType.ASSIGN_UUID)
    private String stationUuid;

    private String cityName;

    private String stationName;

    private String stationEn;

    private Double stationLongitude;

    private Double stationLatitude;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enableDate;

    private String stationStatus;
}