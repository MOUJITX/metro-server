package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName station_vo
 */
@TableName(value ="station_vo")
@Data
public class StationVo {
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