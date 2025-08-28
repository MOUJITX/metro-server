package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemMenu;
import com.moujitx.metro.server.entity.SystemPermission;
import com.moujitx.metro.server.mapper.SystemMenuMapper;
import com.moujitx.metro.server.mapper.SystemPermissionMapper;
import com.moujitx.metro.server.service.ISystemMenuService;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            SystemPermission permission = systemPermissionMapper.selectById(menu.getPermissionId());
            if (permission != null) {
                menu.setRule(permission.getName());
            }
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

    public List<SystemMenu> getMenusByParentId(String parentId, Byte state) {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotBlank(parentId), "parent_id", parentId)
                .isNull(CharSequenceUtil.isBlank(parentId), "parent_id")
                .eq(!StrUtil.isBlankIfStr(state), "state", state);
        List<SystemMenu> menus = this.list(queryWrapper);
        return getMenusPermissionName(menus);
    }

    public List<SystemMenu> getMenuTree(Boolean isShowButton, Byte state) {
        List<SystemMenu> rootMenus = this.getMenusByParentId(null, state);
        for (SystemMenu rootMenu : rootMenus) {
            buildMenuTree(rootMenu, isShowButton, state);
        }
        return rootMenus;
    }

    public Page<SystemMenu> getMenuTreePage(Boolean isShowButton, Byte state, Integer page, Integer pageSize) {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id")
                .eq(!StrUtil.isBlankIfStr(state), "state", state);
        Page<SystemMenu> menuPage = this.page(new Page<>(page, pageSize), queryWrapper);

        List<SystemMenu> records = menuPage.getRecords();
        List<SystemMenu> processedRecords = new ArrayList<>();

        for (SystemMenu rootMenu : records) {
            buildMenuTree(rootMenu, isShowButton, state);
            processedRecords.add(rootMenu);
        }

        Page<SystemMenu> resultPage = new Page<>(menuPage.getCurrent(), menuPage.getSize(), menuPage.getTotal());
        resultPage.setRecords(processedRecords);

        return resultPage;
    }

    private void buildMenuTree(SystemMenu menu, Boolean showButton, Byte state) {
        List<SystemMenu> children = this.getMenusByParentId(menu.getId(), state);

        if (children == null) {
            return;
        }

        List<SystemMenu> filteredChildren = children;
        if (Boolean.FALSE.equals(showButton)) {
            filteredChildren = children.stream()
                    .filter(child -> child.getType() != 3)
                    .collect(Collectors.toList());
        }

        if (!filteredChildren.isEmpty()) {
            menu.setChildren(filteredChildren);
        }

        for (SystemMenu child : children) {
            buildMenuTree(child, showButton, state);
        }
    }

    public SystemMenu add(SystemMenu menu) {
        if (CharSequenceUtil.isNotEmpty(menu.getRule())) {
            SystemPermission permission = new SystemPermission()
                    .setName(menu.getRule())
                    .setDescription(menu.getLabel() + "的权限");
            systemPermissionMapper.insert(permission);
            menu.setPermissionId(permission.getId());
        }

        this.save(menu);
        return menu;
    }

}