package com.moujitx.metro.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.Station;
import com.moujitx.metro.server.service.StationService;
import com.moujitx.metro.server.service.StationVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    StationService stationService;

    @Autowired
    StationVoService stationVoService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(stationVoService.list());
    }

    @GetMapping()
    public Result get(@RequestParam String cityCode, @RequestParam String stationName) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("station_name", stationName);
        return Result.ok(stationService.list(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody Station station) {
        return Result.created(stationService.saveOrUpdate(station));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String uuid) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return Result.ok(stationService.remove(queryWrapper));
    }
}
