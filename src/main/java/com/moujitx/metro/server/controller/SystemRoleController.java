package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemRole;
import com.moujitx.metro.server.service.ISystemRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/system/role")
public class SystemRoleController {
    private final ISystemRoleService systemRoleService;

    @GetMapping("/")
    public Result list() {
        return Result.ok(systemRoleService.list());
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        Page<SystemRole> queryPage = new Page<>(page, pageSize);
        QueryWrapper<SystemRole> queryWrapper = new QueryWrapper<>();
        return Result.ok(systemRoleService.page(queryPage, queryWrapper));
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        return Result.ok(systemRoleService.getById(id));
    }

    @PostMapping()
    public Result add(@RequestBody SystemRole systemRole) {
        systemRoleService.save(systemRole);
        return Result.ok();
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody SystemRole systemRole) {
        systemRole.setId(id);
        systemRoleService.updateById(systemRole);
        return Result.ok();
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        systemRoleService.removeById(id);
        return Result.ok();
    }

}