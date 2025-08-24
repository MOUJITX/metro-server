package com.moujitx.metro.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.Line;
import com.moujitx.metro.server.entity.LineStationVo;
import com.moujitx.metro.server.service.LineService;
import com.moujitx.metro.server.service.LineStationVoService;
import com.moujitx.metro.server.service.LineVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    LineService lineService;

    @Autowired
    LineVoService lineVoService;

    @Autowired
    LineStationVoService lineStationVoService;

    @AuthAccess
    @GetMapping("/list")
    public Result list() {
        return Result.ok(lineVoService.list());
    }

    @AuthAccess
    @GetMapping("/stations")
    public Result stationList() {
        return Result.ok(lineStationVoService.list());
    }

    @AuthAccess
    @GetMapping("/stations/{cityCode}/{lineCode}")
    public Result lineStations(@PathVariable String cityCode, @PathVariable String lineCode) {
        QueryWrapper<LineStationVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("line_code", lineCode);
        return Result.ok(lineStationVoService.list(queryWrapper));
    }
}
