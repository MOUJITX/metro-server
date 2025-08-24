package com.moujitx.metro.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.Bind;
import com.moujitx.metro.server.entity.LineStationVo;
import com.moujitx.metro.server.service.BindService;
import com.moujitx.metro.server.service.LineStationVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bind")
public class BindController {

    @Autowired
    LineStationVoService lineStationVoService;

    @Autowired
    BindService bindService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(lineStationVoService.list());
    }

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam String cityCode, @RequestParam String lineCode) {
        QueryWrapper<LineStationVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("line_code", lineCode);
        return Result.ok(lineStationVoService.list(queryWrapper));
    }

    @AuthAccess
    @PostMapping()
    public Result addOrUpdate(@RequestBody Bind bind) {
        return Result.created(bindService.saveOrUpdate(bind));
    }

    @AuthAccess
    @DeleteMapping()
    public Result delete(@RequestParam String uuid) {
        QueryWrapper<Bind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return Result.ok(bindService.remove(queryWrapper));
    }
}
