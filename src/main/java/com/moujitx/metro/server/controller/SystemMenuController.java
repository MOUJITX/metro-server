package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.service.ISystemMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

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
}