package com.moujitx.metro.server.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author MOUJITX
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("metro_station_vo")
public class MetroStationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @TableField("id")
    private String id;

    @OrderBy(sort = 1, asc = true)
    @TableField("city_code")
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    @TableField("city_spell")
    private String citySpell;

    @TableField("station_name")
    private String stationName;

    @TableField("station_en")
    private String stationEn;

    /**
     * 经度
     */
    @TableField("station_longitude")
    private Double stationLongitude;

    /**
     * 纬度
     */
    @TableField("station_latitude")
    private Double stationLatitude;

    @TableField("enable_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enableDate;

    /**
     * 运营、建设、规划、撤销
     */
    @OrderBy(sort = 2, asc = true)
    @TableField("station_status")
    private String stationStatus;

}