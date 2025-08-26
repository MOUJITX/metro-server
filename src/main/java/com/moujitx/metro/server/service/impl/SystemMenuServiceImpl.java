package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemMenu;
import com.moujitx.metro.server.mapper.SystemMenuMapper;
import com.moujitx.metro.server.mapper.SystemPermissionMapper;
import com.moujitx.metro.server.service.ISystemMenuService;
import com.moujitx.metro.server.service.ISystemUserRoleService;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MOUJITX
 */
@Service
@RequiredArgsConstructor
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    private final SystemPermissionMapper systemPermissionMapper;

    public void getMenuPermissionName(SystemMenu menu) {
        if (CharSequenceUtil.isNotBlank(menu.getPermissionId())) {
            String permission = systemPermissionMapper.selectById(menu.getPermissionId()).getName();
            menu.setRule(permission);
            menu.setKey(menu.getRouter());
        }
    }

    public SystemMenu getMenuById(String id) {
        SystemMenu menu = this.getById(id);
        if (menu != null) {
            this.getMenuPermissionName(menu);
        }
        return menu;
    }

    public List<SystemMenu> getMenusPermissionName(List<SystemMenu> menus) {
        for (SystemMenu menu : menus) {
            this.getMenuPermissionName(menu);
        }
        return menus;
    }

    public List<SystemMenu> getMenusByParentId() {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id");
        List<SystemMenu> menus = this.list(queryWrapper);
        return getMenusPermissionName(menus);
    }

    public List<SystemMenu> getMenusByParentId(String parentId) {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<SystemMenu> menus = this.list(queryWrapper);
        return getMenusPermissionName(menus);
    }

}