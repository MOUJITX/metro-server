package com.moujitx.metro.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.entity.MetroLineStation;
import com.moujitx.metro.server.entity.MetroLineStationVo;
import com.moujitx.metro.server.entity.MetroLineVo;
import com.moujitx.metro.server.service.IMetroLineVoService;
import org.springframework.web.bind.annotation.*;

import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.service.IMetroLineStationService;
import com.moujitx.metro.server.service.IMetroLineStationVoService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author MOUJITX
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/metro/line/station")
public class MetroLineStationController {
    private final IMetroLineVoService metroLineVoService;
    private final IMetroLineStationService metroLineStationService;
    private final IMetroLineStationVoService metroLineStationVoService;

    @GetMapping("/")
    public Result getStations(@RequestParam String line) {
        List<MetroLineStationVo> stations = metroLineStationVoService.getStationsByLine(line);
        MetroLineVo lineInfo = metroLineVoService.getById(line);

        Map<String, Object> map = new HashMap<>();
        map.put("line", lineInfo);
        map.put("stations", stations);
        return Result.ok(map);
    }

    @PostMapping("/multiple")
    public Result getStations(@RequestBody List<String> lineIds) {
        List<Object> lines = new ArrayList<>();

        lineIds.forEach(lineId -> {
            MetroLineVo lineInfo = metroLineVoService.getById(lineId);

            if (lineInfo == null) {
                return;
            }

            QueryWrapper<MetroLineStationVo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("line_code", lineId);
            List<MetroLineStationVo> stations = metroLineStationVoService.list(queryWrapper);

            Map<String, Object> map = new HashMap<>();
            map.put("line", lineInfo);
            map.put("stations", stations);
            lines.add(map);
        });

        return Result.ok(lines);
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        return Result.ok(metroLineStationService.getById(id));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(metroLineStationService.removeById(id));
    }

    @PostMapping()
    public Result save(@RequestParam String line, @RequestBody MetroLineStation metroLineStation) {
        metroLineStation.setLineCode(line);
        return Result.ok(metroLineStationService.save(metroLineStation));
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody MetroLineStation metroLineStation) {
        metroLineStation.setId(id);
        return Result.ok(metroLineStationService.updateById(metroLineStation));
    }
}