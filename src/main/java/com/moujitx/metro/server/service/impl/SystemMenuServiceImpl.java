package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemMenu;
import com.moujitx.metro.server.mapper.SystemMenuMapper;
import com.moujitx.metro.server.service.ISystemMenuService;
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

    public List<SystemMenu> getMenuTree() {
        List<SystemMenu> rootMenus = this.getRootMenus(true);
        for (SystemMenu rootMenu : rootMenus) {
            buildMenuTree(rootMenu, false, true);
        }
        return rootMenus;
    }

    public Page<SystemMenu> getMenuButtonTreePage(Boolean state, Integer page, Integer pageSize) {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id")
                .eq(state != null, "state", state);
        Page<SystemMenu> menuPage = this.page(new Page<>(page, pageSize), queryWrapper);

        List<SystemMenu> records = menuPage.getRecords();
        List<SystemMenu> processedRecords = new ArrayList<>();

        for (SystemMenu rootMenu : records) {
            buildMenuTree(rootMenu, true, state);
            processedRecords.add(rootMenu);
        }

        Page<SystemMenu> resultPage = new Page<>(page, pageSize, menuPage.getTotal());
        resultPage.setRecords(processedRecords);

        return resultPage;
    }

    public List<SystemMenu> getRootMenus(Boolean state) {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id")
                .eq(state != null, "state", state);
        return this.list(queryWrapper);
    }

    public List<SystemMenu> getMenusByParentId(String parentId, Boolean state) {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId)
                .eq(state != null, "state", state);
        return this.list(queryWrapper);
    }

    private void buildMenuTree(SystemMenu menu, Boolean showButton, Boolean state) {
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

    public List<String> getMenuRoutersByIds(List<String> ids) {
        return this.listByIds(ids).stream()
                .map(SystemMenu::getRouter)
                .collect(Collectors.toList());
    }

}