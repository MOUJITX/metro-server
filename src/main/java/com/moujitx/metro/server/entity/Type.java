package com.moujitx.metro.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @TableName type
 */
@TableName(value = "type")
@Data
public class Type {

    @OrderBy(sort = 1)
    @TableId(type = IdType.ASSIGN_ID)
    private String typeCode;

    private String typeName;

    private String typeLevel;
}