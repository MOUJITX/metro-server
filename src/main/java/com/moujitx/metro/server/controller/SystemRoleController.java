package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.service.ISystemRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author MOUJITX
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
public class SystemRoleController {
    private final ISystemRoleService systemRoleService;

    @AuthAccess
    @GetMapping("/list")
    public Result list() {
        return Result.ok(systemRoleService.list());
    }
}