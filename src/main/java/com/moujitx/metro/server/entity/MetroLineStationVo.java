package com.moujitx.metro.server.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("metro_line_station_vo")
public class MetroLineStationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("bind_uuid")
    @TableId(value = "bind_uuid", type = IdType.ASSIGN_UUID)
    private String bindUuid;

    @TableField("city_name")
    private String cityName;

    @TableField("line_name")
    private String lineName;

    @TableField("station_name")
    private String stationName;

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

    @TableField("sort")
    private Integer sort;

    @TableField("city_code")
    private String cityCode;

    @TableField("line_code")
    private String lineCode;

}