package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.moujitx.metro.server.service.ISystemRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author MOUJITX
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
public class SystemRoleController {
    private final ISystemRoleService systemRoleService;

}