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
import com.moujitx.metro.server.entity.MetroLineStation;
import com.moujitx.metro.server.entity.MetroLineStationVo;
import com.moujitx.metro.server.service.IMetroLineStationService;
import com.moujitx.metro.server.service.IMetroLineStationVoService;

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
@RequestMapping("/metro/lineStation")
public class MetroLineStationController {
    private final IMetroLineStationService metroLineStationService;
    private final IMetroLineStationVoService metroLineStationVoService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(metroLineStationVoService.list());
    }

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam String cityCode, @RequestParam String lineCode) {
        QueryWrapper<MetroLineStationVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("line_code", lineCode);
        return Result.ok(metroLineStationVoService.list(queryWrapper));
    }

    @AuthAccess
    @PostMapping()
    public Result addOrUpdate(@RequestBody MetroLineStation bind) {
        return Result.created(metroLineStationService.saveOrUpdate(bind));
    }

    @AuthAccess
    @DeleteMapping()
    public Result delete(@RequestParam String uuid) {
        QueryWrapper<MetroLineStation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return Result.ok(metroLineStationService.remove(queryWrapper));
    }
}