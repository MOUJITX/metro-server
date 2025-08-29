package com.moujitx.metro.server.controller;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moujitx.metro.server.entity.MetroStationVo;
import com.moujitx.metro.server.entity.MetroType;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.MetroStation;
import com.moujitx.metro.server.service.IMetroStationService;
import com.moujitx.metro.server.service.IMetroStationVoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @GetMapping("/")
    public Result list() {
        return Result.ok(metroStationVoService.list());
    }

    @GetMapping("/page")
    public Result page(@RequestParam Integer page,
                       @RequestParam Integer pageSize,
                       @RequestParam(required = false) String cityCode,
                       @RequestParam(required = false) String stationName,
                       @RequestParam(required = false) String stationStatus) {
        Page<MetroStationVo> queryPage = new Page<>(page, pageSize);

        QueryWrapper<MetroStationVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotBlank(cityCode), "city_code", cityCode)
                .like(CharSequenceUtil.isNotBlank(stationName), "station_name", stationName)
                .eq(CharSequenceUtil.isNotBlank(stationStatus), "station_status", stationStatus);

        return Result.ok(metroStationVoService.page(queryPage,queryWrapper));
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        return Result.ok(metroStationService.getById(id));
    }

    @PostMapping()
    public Result add(@RequestBody MetroStation station) {
        return Result.ok(metroStationService.save(station));
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody MetroStation station) {
        station.setId(id);
        return Result.ok(metroStationService.updateById(station));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(metroStationService.removeById(id));
    }

    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> userIds) {
        return Result.ok(metroStationService.removeByIds(userIds, true));
    }

}