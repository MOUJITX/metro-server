package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.SystemUser;
import com.moujitx.metro.server.entity.SystemUserLogin;
import com.moujitx.metro.server.service.ISystemUserService;
import com.moujitx.metro.server.utils.TokenUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/")
    @AuthAccess
    public Result list() {
        return Result.ok(systemUserService.list());
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

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("token", TokenUtils.generateToken(user.getId()));
        resMap.put("user", resUser);
        resMap.put("permissions", permissions);

        return Result.ok(resMap);
    }

}