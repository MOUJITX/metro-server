package com.moujitx.metro.server.controller;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.MetroCity;
import com.moujitx.metro.server.service.IMetroCityService;
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
@RequestMapping("/metro/city")
public class MetroCityController {
    private final IMetroCityService metroCityService;

    @GetMapping("/")
    public Result list() {
        return Result.ok(metroCityService.list());
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String key) {
        Page<MetroCity> queryPage = new Page<>(page, pageSize);

        QueryWrapper<MetroCity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(CharSequenceUtil.isNotBlank(key), "city_name", key)
                .or()
                .like(CharSequenceUtil.isNotBlank(key), "city_spell", key);

        return Result.ok(metroCityService.page(queryPage, queryWrapper));
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        return Result.ok(metroCityService.getById(id));
    }

    @PostMapping()
    public Result add(@RequestBody MetroCity city) {
        return Result.ok(metroCityService.save(city));
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody MetroCity city) {
        city.setId(id);
        return Result.ok(metroCityService.updateById(city));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(metroCityService.removeById(id));
    }

    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<String> userIds) {
        return Result.ok(metroCityService.removeByIds(userIds, true));
    }
}