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
import com.moujitx.metro.server.entity.MetroCity;
import com.moujitx.metro.server.service.IMetroCityService;
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
@RequestMapping("/metro/city")
public class MetroCityController {
    private final IMetroCityService metroCityService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(metroCityService.list());
    }

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam String cityCode) {
        QueryWrapper<MetroCity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        return Result.ok(metroCityService.getOne(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody MetroCity city) {
        return Result.created(metroCityService.saveOrUpdate(city));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String cityCode) {
        QueryWrapper<MetroCity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        return Result.ok(metroCityService.remove(queryWrapper));
    }
}