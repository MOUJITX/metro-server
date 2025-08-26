package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemUser;
import com.moujitx.metro.server.entity.SystemUserLogin;
import com.moujitx.metro.server.entity.SystemUserRole;
import com.moujitx.metro.server.service.ISystemPermissionService;
import com.moujitx.metro.server.service.ISystemRolePermissionService;
import com.moujitx.metro.server.service.ISystemUserRoleService;
import com.moujitx.metro.server.service.ISystemUserService;
import com.moujitx.metro.server.utils.TokenUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author MOUJITX
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
public class SystemUserController {
    private final ISystemUserService systemUserService;
    private final ISystemUserRoleService systemUserRoleService;
    private final ISystemRolePermissionService systemRolePermissionService;
    private final ISystemPermissionService systemPermissionService;

    @GetMapping("/")
    @AuthAccess
    public Result list() {
        return Result.ok(systemUserService.page(null));
    }

    @AuthAccess
    @GetMapping("/page")
    public Result page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return Result.ok(systemUserService.page(page, pageSize));
    }

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam String id) {
        SystemUser user = systemUserService.getById(id);
        List<String> roleIds = systemUserRoleService.getUserRolesByUserId(id)
                .stream()
                .map(SystemUserRole::getRoleId)
                .collect(Collectors.toList());
        user.setRoleIds(roleIds);
        return Result.ok(user);
    }

    @AuthAccess
    @DeleteMapping("/{userId}")
    public Result logicDelete(@PathVariable String userId) {
        return Result.ok(systemUserService.removeById(userId));
    }

    @AuthAccess
    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> userIds) {
        return Result.ok(systemUserService.removeByIds(userIds, true));
    }

    @AuthAccess
    @PostMapping("/login")
    public Result login(@RequestBody SystemUserLogin userLogin) {
        SystemUser user = systemUserService.authenticate(userLogin.getUsername(), userLogin.getPassword());

        Map<String, String> resUser = new HashMap<>();
        resUser.put("id", user.getId());
        resUser.put("username", user.getUsername());
        resUser.put("email", user.getEmail());

        List<String> permissions = new ArrayList<>();
        systemUserRoleService.getUserRolesByUserId(user.getId()).forEach(
                role -> systemRolePermissionService.getRolePermissionsByRoleId(role.getRoleId()).forEach(
                        permission -> permissions.add(systemPermissionService.getPermissionById(
                                permission.getPermissionId()).getName())));

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("token", TokenUtils.generateToken(user.getId()));
        resMap.put("user", resUser);
        resMap.put("permissions", permissions);

        return Result.ok(resMap);
    }

}