package com.moujitx.metro.server.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MOUJITX
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("metro_type")
public class MetroType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @TableField("id")
    private String id;

    @TableField("type_name")
    private String typeName;

    @TableField("type_level")
    private String typeLevel;

    @OrderBy(sort = 1, asc = true)
    @TableField("type_sort")
    private Integer typeSort;

}