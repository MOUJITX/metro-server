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
import com.moujitx.metro.server.entity.MetroStation;
import com.moujitx.metro.server.service.IMetroStationService;
import com.moujitx.metro.server.service.IMetroStationVoService;

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
@RequestMapping("/metro/station")
public class MetroStationController {
    private final IMetroStationService metroStationService;
    private final IMetroStationVoService metroStationVoService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(metroStationVoService.list());
    }

    @GetMapping()
    public Result get(@RequestParam String cityCode, @RequestParam String stationName) {
        QueryWrapper<MetroStation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("station_name", stationName);
        return Result.ok(metroStationService.list(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody MetroStation station) {
        return Result.ok(metroStationService.saveOrUpdate(station));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String uuid) {
        QueryWrapper<MetroStation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return Result.ok(metroStationService.remove(queryWrapper));
    }

}