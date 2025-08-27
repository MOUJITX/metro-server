package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemUser;
import com.moujitx.metro.server.entity.SystemUserLogin;
import com.moujitx.metro.server.entity.SystemUserRole;
import com.moujitx.metro.server.exception.NotFoundException;
import com.moujitx.metro.server.service.ISystemPermissionService;
import com.moujitx.metro.server.service.ISystemRolePermissionService;
import com.moujitx.metro.server.service.ISystemUserRoleService;
import com.moujitx.metro.server.service.ISystemUserService;
import com.moujitx.metro.server.utils.TokenUtils;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Result list() {
        return Result.ok(systemUserService.page(null));
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "false") Boolean fuzzy,
            @RequestParam(defaultValue = "") String username) {
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotBlank(username) && Boolean.FALSE.equals(fuzzy), "username", username)
                .like(Boolean.TRUE.equals(fuzzy), "username", username);

        Page<SystemUser> users = systemUserService.page(page, pageSize, queryWrapper);

        IPage<SystemUser> usersPage = users.convert(user -> {
            List<String> roleNames = systemUserRoleService.getUserRoleNamesByUserId(user.getId());
            user.setRolesName(roleNames);
            return user;
        });

        return Result.ok(usersPage);
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        SystemUser user = systemUserService.getById(id);
        if (user == null) {
            throw new NotFoundException("user not found");
        }
        List<String> roleIds = systemUserRoleService.getUserRolesByUserId(id)
                .stream()
                .map(SystemUserRole::getRoleId)
                .collect(Collectors.toList());
        user.setRoleIds(roleIds);
        return Result.ok(user);
    }

    @PostMapping()
    public Result save(@RequestBody SystemUser user) {
        String userId = systemUserService.addUser(user);
        systemUserRoleService.saveOrUpdateUserRoles(userId, user.getRoleIds());
        return Result.ok();
    }

    @PutMapping()
    public Result update(@RequestParam(name = "id") String userId, @RequestBody SystemUser user) {
        user.setId(userId);
        systemUserService.updateById(user);
        if (!user.getRoleIds().isEmpty()) {
            systemUserRoleService.saveOrUpdateUserRoles(userId, user.getRoleIds());
        }
        return Result.ok();
    }

    @DeleteMapping()
    public Result logicDelete(@RequestParam(name = "id") String userId) {
        return Result.ok(systemUserService.removeById(userId));
    }

    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> userIds) {
        return Result.ok(systemUserService.removeByIds(userIds, true));
    }

    public Map<String, Object> mapLoginResult(SystemUser user, String token) {
        if (token == null) {
            token = TokenUtils.generateToken(user.getId());
        }

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
        resMap.put("token", token);
        resMap.put("user", resUser);
        resMap.put("permissions", permissions);

        return resMap;
    }

    @AuthAccess
    @PostMapping("/login")
    public Result login(@RequestBody SystemUserLogin userLogin) {
        SystemUser user = systemUserService.authenticate(userLogin.getUsername(), userLogin.getPassword());
        return Result.ok(mapLoginResult(user, null));
    }

    @GetMapping("/refreshPermissions")
    public Result postMethodName(
            @RequestParam(name = "refresh_cache") Boolean refreshCache,
            HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        String userId = TokenUtils.getUUID(token);
        SystemUser user = systemUserService.getById(userId);
        return Result.ok(mapLoginResult(user, Boolean.TRUE.equals(refreshCache) ? null : token));
    }

}