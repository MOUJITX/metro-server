package com.moujitx.metro.server.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("metro_line_vo")
public class MetroLineVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @TableField("id")
    private String id;

    @OrderBy(sort = 1, asc = true)
    @TableField("city_code")
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    @TableField("city_spell")
    private String citySpell;

    @OrderBy(sort = 3, asc = true)
    @TableField("line_code")
    private String lineCode;

    @TableField("line_name")
    private String lineName;

    @TableField("line_en")
    private String lineEn;

    @TableField("line_cycle")
    private Boolean lineCycle;

    @TableField("line_color")
    private String lineColor;

    @TableField("type_id")
    private String typeId;

    @TableField("type_name")
    private String typeName;

    @TableField("enable_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enableDate;

    @TableField("line_company")
    private String lineCompany;

    @TableField("line_logo")
    private String lineLogo;

    /**
     * 运营、建设、规划、撤销
     */
    @OrderBy(sort = 2, asc = true)
    @TableField("line_status")
    private String lineStatus;

}