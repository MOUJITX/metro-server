package com.moujitx.metro.server.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableId(value = "city_code", type = IdType.ASSIGN_ID)
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    @TableField("city_spell")
    private String citySpell;
}