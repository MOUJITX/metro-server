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
@TableName("metro_city")
public class MetroCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @TableField(value = "id")
    private String id;

    @OrderBy(sort = 1, asc = true)
    @TableField("city_code")
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    @TableField("city_spell")
    private String citySpell;
}