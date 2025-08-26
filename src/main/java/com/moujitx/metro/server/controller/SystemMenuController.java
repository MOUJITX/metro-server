package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.moujitx.metro.server.service.ISystemMenuService;
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
@RequestMapping("/SystemMenu")
public class SystemMenuController {
    private final ISystemMenuService systemMenuService;

}