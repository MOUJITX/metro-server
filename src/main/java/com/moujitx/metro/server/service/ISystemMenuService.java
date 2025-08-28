package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.SystemMenu;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    List<SystemMenu> getMenuTree();

    Page<SystemMenu> getMenuButtonTreePage(Boolean state, Integer page, Integer pageSize);

    List<String> getMenuRoutersByIds(List<String> ids);

}