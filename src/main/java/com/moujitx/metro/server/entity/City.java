package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @TableName city
 */
@TableName(value = "city")
@Data
public class City {

    @OrderBy(sort = 1)
    @TableId(type = IdType.ASSIGN_ID)
    private String cityCode;

    private String cityName;

    private String citySpell;
}