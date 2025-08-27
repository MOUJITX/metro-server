package com.moujitx.metro.server.entity;

import java.time.LocalDate;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableId(value = "station_uuid", type = IdType.ASSIGN_UUID)
    private String stationUuid;

    @TableField("city_name")
    private String cityName;

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
    @TableField("station_status")
    private String stationStatus;

}