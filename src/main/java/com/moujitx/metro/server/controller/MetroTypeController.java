package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.MetroType;
import com.moujitx.metro.server.service.IMetroTypeService;

import cn.hutool.core.text.CharSequenceUtil;
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
@RequestMapping("/MetroType")
public class MetroTypeController {
    private final IMetroTypeService metroTypeService;

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam(defaultValue = "") String typeCode) {
        QueryWrapper<MetroType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotBlank(typeCode), "type_code", typeCode);
        return Result.ok(metroTypeService.list(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody MetroType type) {
        return Result.created(metroTypeService.saveOrUpdate(type));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String typeCode) {
        QueryWrapper<MetroType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", typeCode);
        return Result.ok(metroTypeService.remove(queryWrapper));
    }
}