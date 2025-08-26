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
import com.moujitx.metro.server.entity.MetroLine;
import com.moujitx.metro.server.service.IMetroLineService;
import com.moujitx.metro.server.service.IMetroLineVoService;

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
@RequestMapping("/MetroLine")
public class MetroLineController {
    private final IMetroLineService metroLineService;

    private final IMetroLineVoService metroLineVoService;

    @AuthAccess
    @GetMapping("/")
    public Result list() {
        return Result.ok(metroLineVoService.list());
    }

    @GetMapping()
    public Result get(@RequestParam String cityCode, @RequestParam String lineCode) {
        QueryWrapper<MetroLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode).eq("line_code", lineCode);
        return Result.ok(metroLineService.list(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody MetroLine line) {
        return Result.created(metroLineService.saveOrUpdate(line));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String uuid) {
        QueryWrapper<MetroLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return Result.ok(metroLineService.remove(queryWrapper));
    }
}