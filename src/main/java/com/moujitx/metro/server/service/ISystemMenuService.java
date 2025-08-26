package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.SystemMenu;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface ISystemMenuService extends IService<SystemMenu> {
    SystemMenu getMenuById(String id);

    List<SystemMenu> getMenusByParentId();

    List<SystemMenu> getMenusByParentId(String parentId);
}