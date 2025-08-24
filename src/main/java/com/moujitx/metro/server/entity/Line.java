package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName line
 */
@TableName(value ="line")
@Data
public class Line {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String cityCode;

    private String lineCode;

    private String lineName;

    private String lineEn;

    private Integer lineCycle;

    private String lineColor;

    private String typeCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enableDate;

    private String lineCompany;

    private String lineLogo;

    private String lineStatus;
}