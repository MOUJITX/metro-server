package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemRoleMenu;
import com.moujitx.metro.server.entity.SystemUserRole;
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

    public void addOrUpdateRoleMenu(String roleId,  List<String> menuIds) {
        this.getRoleMenusByRoleId(roleId).forEach(
                savedMenu -> {
                    if (!menuIds.contains(savedMenu.getMenuId()))
                        this.removeById(savedMenu.getId());
                }
        );

        menuIds.forEach(
                menuId -> {
                    if (!this.getMenuIdsByRoleId(roleId).contains(menuId)){
                        SystemRoleMenu roleMenu = new SystemRoleMenu()
                                .setRoleId(roleId)
                                .setMenuId(menuId);
                        this.save(roleMenu);
                    }
                }
        );
    }

    public List<SystemRoleMenu> getRoleMenusByRoleId(String roleId) {
        QueryWrapper<SystemRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return this.list(queryWrapper);
    }



    public List<String> getMenuIdsByRoleId(String roleId) {
        List<String> menuIds = new ArrayList<>();
        this.getRoleMenusByRoleId(roleId).forEach(roleMenu -> menuIds.add(roleMenu.getMenuId()));
        return menuIds;
    }

}