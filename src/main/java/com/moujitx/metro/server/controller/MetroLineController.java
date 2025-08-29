package com.moujitx.metro.server.controller;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moujitx.metro.server.entity.MetroLineVo;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.MetroLine;
import com.moujitx.metro.server.service.IMetroLineService;
import com.moujitx.metro.server.service.IMetroLineVoService;

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
@RequestMapping("/metro/line")
public class MetroLineController {
    private final IMetroLineService metroLineService;
    private final IMetroLineVoService metroLineVoService;

    @GetMapping("/")
    public Result list() {
        return Result.ok(metroLineVoService.list());
    }

    @GetMapping("/page")
    public Result page(@RequestParam Integer page,
                      @RequestParam Integer pageSize,
                      @RequestParam(required = false) String cityCode,
                      @RequestParam(required = false) String lineName,
                      @RequestParam(required = false) String lineStatus) {
        Page<MetroLineVo> queryPage = new Page<>(page, pageSize);

        QueryWrapper<MetroLineVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotBlank(cityCode), "city_code", cityCode)
                .like(CharSequenceUtil.isNotBlank(lineName),"line_name", lineName)
                .eq(CharSequenceUtil.isNotBlank(lineStatus), "line_status", lineStatus);

        return Result.ok(metroLineVoService.page(queryPage,queryWrapper));
    }

    @GetMapping()
    public Result get(@RequestParam String id) { return Result.ok(metroLineService.getById(id));}

    @PostMapping()
    public Result add(@RequestBody MetroLine line) {
        return Result.ok(metroLineService.save(line));
    }

    @PutMapping
    public Result update(@RequestParam String id,@RequestBody MetroLine line) {
        line.setId(id);
        return Result.ok(metroLineService.updateById(line));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(metroLineService.removeById(id));
    }

    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> userIds) {
        return Result.ok(metroLineService.removeByIds(userIds, true));
    }
}