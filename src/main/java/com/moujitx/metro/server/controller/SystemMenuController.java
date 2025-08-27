package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemMenu;
import com.moujitx.metro.server.service.ISystemMenuService;
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
        return Result.ok(systemMenuService.getById(id));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(systemMenuService.removeById(id));
    }

    @PostMapping()
    public Result add(@RequestBody SystemMenu menu) {
        systemMenuService.save(menu);
        menu.getActions().forEach(action -> {
            SystemMenu actionMenu = new SystemMenu();
            actionMenu.setLabel(action);
            actionMenu.setLabelEn(action);
            actionMenu.setType(3);
            actionMenu.setRouter(menu.getRouter() + "/" + action);
            actionMenu.setParentId(menu.getId());
            systemMenuService.add(actionMenu);
        });
        return Result.ok();
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody SystemMenu menu) {
        menu.setId(id);
        return Result.ok(systemMenuService.updateById(menu));
    }

}