package com.moujitx.metro.server.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
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
@TableName("system_menu")
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("label")
    private String label;

    @TableField("label_en")
    private String labelEn;

    @TableField("type")
    private Integer type;

    @TableField("icon")
    private String icon;

    @TableField("router")
    private String router;

    @OrderBy(sort = 1, asc = true)
    @TableField("sort")
    private Integer sort;

    @TableField("state")
    private Boolean state;

    @TableField("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @TableField("parent_id")
    private String parentId;

    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField(exist = false)
    private List<SystemMenu> children;

    @TableField(exist = false)
    private List<String> actions;
}