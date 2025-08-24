package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @TableName type
 */
@TableName(value ="type")
@Data
public class Type {

    @OrderBy(sort = 1)
    private String typeCode;

    private String typeName;

    private String typeLevel;
}