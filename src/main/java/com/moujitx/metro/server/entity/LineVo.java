package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName line_vo
 */
@TableName(value ="line_vo")
@Data
public class LineVo {
    private String lineUuid;

    private String cityCode;

    private String cityName;

    private String lineName;

    private Integer lineCycle;

    private String typeName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enableDate;

    private String lineColor;

    private String typeCode;

    private String lineLogo;

    private String lineStatus;

    private String lineEn;

    private String lineCompany;

    private String lineCode;
}