package com.moujitx.metro.server.entity;

import java.time.LocalDate;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableId(value = "line_uuid", type = IdType.ASSIGN_UUID)
    private String lineUuid;

    @TableField("city_code")
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    @TableField("line_name")
    private String lineName;

    @TableField("line_cycle")
    private Byte lineCycle;

    @TableField("type_name")
    private String typeName;

    @TableField("enable_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enableDate;

    @TableField("line_color")
    private String lineColor;

    @TableField("type_code")
    private String typeCode;

    @TableField("line_logo")
    private String lineLogo;

    /**
     * 运营、建设、规划、撤销
     */
    @TableField("line_status")
    private String lineStatus;

    @TableField("line_en")
    private String lineEn;

    @TableField("line_company")
    private String lineCompany;

    @TableField("line_code")
    private String lineCode;

}