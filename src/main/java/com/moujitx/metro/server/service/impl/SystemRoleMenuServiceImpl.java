package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemRoleMenu;
import com.moujitx.metro.server.mapper.SystemRoleMenuMapper;
import com.moujitx.metro.server.service.ISystemRoleMenuService;

import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
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
public class SystemRoleMenuServiceImpl extends ServiceImpl<SystemRoleMenuMapper, SystemRoleMenu>
        implements ISystemRoleMenuService {

    private final SystemRoleMenuMapper systemRoleMenuMapper;

    public void addRoleMenu(String roleId, String menuId) {
        SystemRoleMenu roleMenu = new SystemRoleMenu()
                .setRoleId(roleId)
                .setMenuId(menuId);
        systemRoleMenuMapper.insert(roleMenu);
    }

    public List<SystemRoleMenu> getRoleMenus(String roleId) {
        QueryWrapper<SystemRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return systemRoleMenuMapper.selectList(queryWrapper);
    }

    public List<String> getMenuIdsByRoleId(String roleId) {
        List<String> menuIds = new ArrayList<>();
        this.getRoleMenus(roleId).forEach(roleMenu -> menuIds.add(roleMenu.getMenuId()));
        return menuIds;
    }

}