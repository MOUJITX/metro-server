package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemMenu;
import com.moujitx.metro.server.entity.SystemPermission;
import com.moujitx.metro.server.service.ISystemMenuService;
import com.moujitx.metro.server.service.ISystemPermissionService;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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
    private final ISystemPermissionService systemPermissionService;

    @GetMapping("/")
    public Result list() {
        return Result.ok(systemMenuService.getMenuTree(false));
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return Result.ok(systemMenuService.getMenuTreePage(true, page, pageSize));
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        return Result.ok(systemMenuService.getMenuById(id));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(systemMenuService.removeById(id));
    }

    @PostMapping()
    public Result add(@RequestBody SystemMenu menu) {
        SystemMenu savedMenu = systemMenuService.add(menu);

        if (menu.getActions() != null) {
            menu.getActions().forEach(action -> {
                SystemMenu actionMenu = new SystemMenu()
                        .setLabel(action)
                        .setLabelEn(action)
                        .setType(3)
                        .setRouter(savedMenu.getRouter() + "/" + action)
                        .setParentId(savedMenu.getId());

                if (CharSequenceUtil.isNotEmpty(menu.getRule())) {
                    actionMenu.setRule(savedMenu.getRule() + "/" + action);
                }

                systemMenuService.add(actionMenu);
            });
        }

        return Result.ok();
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody SystemMenu menu) {
        if (menu.getParentId() != null && menu.getParentId().equals(id)) {
            return Result.badRequest("Should not be parent of itself.");
        }

        SystemMenu oldMenu = systemMenuService.getMenuById(id);
        if (oldMenu.getRule() == null && menu.getRule() != null) {
            SystemPermission permission = new SystemPermission()
                    .setName(menu.getRule())
                    .setDescription(menu.getLabel() + "的权限");
            systemPermissionService.save(permission);
            menu.setPermissionId(permission.getId());
        }

        if (oldMenu.getRule() != null && menu.getRule() == null) {
            systemPermissionService.removeById(oldMenu.getPermissionId());
        }

        if (oldMenu.getRule() != null && menu.getRule() != null
                && !CharSequenceUtil.equals(oldMenu.getRule(), menu.getRule())) {
            SystemPermission permission = new SystemPermission().setId(menu.getPermissionId()).setName(menu.getRule());
            systemPermissionService.updateById(permission);
        }

        menu.setId(id);
        return Result.ok(systemMenuService.updateById(menu));
    }

}