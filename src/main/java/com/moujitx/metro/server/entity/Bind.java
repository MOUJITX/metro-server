package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName bind
 */
@TableName(value = "bind")
@Data
public class Bind {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String lineCode;

    private String stationCode;

    private Integer order;
}