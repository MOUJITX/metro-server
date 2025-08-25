package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @TableName station
 */
@TableName(value = "station")
@Data
public class Station {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String cityCode;

    private String stationName;

    private String stationEn;

    private Double stationLongitude;

    private Double stationLatitude;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enableDate;

    private String stationStatus;
}