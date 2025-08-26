package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemMenu;
import com.moujitx.metro.server.service.ISystemMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author MOUJITX
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/menu")
public class SystemMenuController {
    private final ISystemMenuService systemMenuService;

    @AuthAccess
    @GetMapping("/list")
    public Result list() {
        List<SystemMenu> rootMenus = systemMenuService.getMenusByParentId();
        for (SystemMenu rootMenu : rootMenus) {
            buildMenuTree(rootMenu);
        }
        return Result.ok(rootMenus);
    }

    // 构建菜单树的方法
    private void buildMenuTree(SystemMenu menu) {
        List<SystemMenu> children = systemMenuService.getMenusByParentId(menu.getId());

        if (children == null) {
            return;
        }

        List<SystemMenu> filteredChildren = children.stream()
                .filter(child -> child.getType() != 3)
                .collect(Collectors.toList());

        menu.setChildren(filteredChildren);

        for (SystemMenu child : children) {
            buildMenuTree(child);
        }
    }

}