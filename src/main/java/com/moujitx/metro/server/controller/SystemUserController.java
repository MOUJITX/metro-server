package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.moujitx.metro.server.service.ISystemUserService;
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
@RequestMapping("/SystemUser")
public class SystemUserController {
    private final ISystemUserService systemUserService;

}