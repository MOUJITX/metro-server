package com.moujitx.metro.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.Line;
import com.moujitx.metro.server.service.LineService;
import com.moujitx.metro.server.service.LineVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    LineService lineService;

    @Autowired
    LineVoService lineVoService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(lineVoService.list());
    }

    @GetMapping()
    public Result get(@RequestParam String cityCode, @RequestParam String lineCode) {
        QueryWrapper<Line> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("line_code", lineCode);
        return Result.ok(lineService.list(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody Line line) {
        return Result.created(lineService.saveOrUpdate(line));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String uuid) {
        QueryWrapper<Line> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return Result.ok(lineService.remove(queryWrapper));
    }
}
