package com.moujitx.metro.server.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
@TableName("metro_line_station_vo")
public class MetroLineStationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("line_code")
    @OrderBy(sort = 1, asc = true)
    private String lineCode;

    @TableField("station_code")
    private String stationCode;

    @TableField("station_name")
    private String stationName;

    @TableField("station_en")
    private String stationEn;

    @TableField("station_longitude")
    private Double stationLongitude;

    @TableField("station_latitude")
    private Double stationLatitude;

    @TableField("enable_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enableDate;

    @TableField("station_status")
    private String stationStatus;

    @TableField("station_sort")
    @OrderBy(sort = 2, asc = true)
    private Integer stationSort;

    @TableField(exist = false)
    private List<MetroLineStation> transferLine;

}