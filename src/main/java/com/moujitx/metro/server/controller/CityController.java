package com.moujitx.metro.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.City;
import com.moujitx.metro.server.service.CityService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(cityService.list());
    }

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam String cityCode) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        return Result.ok(cityService.getOne(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody City city) {
        return Result.created(cityService.saveOrUpdate(city));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String cityCode) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        return Result.ok(cityService.remove(queryWrapper));
    }
}
