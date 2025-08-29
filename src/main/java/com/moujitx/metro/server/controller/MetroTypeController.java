package com.moujitx.metro.server.controller;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.MetroType;
import com.moujitx.metro.server.service.IMetroTypeService;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author MOUJITX
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/metro/type")
public class MetroTypeController {
    private final IMetroTypeService metroTypeService;

    @GetMapping("/")
    public Result list() {
        return Result.ok(metroTypeService.list());
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String typeLevel,
            @RequestParam(required = false) String typeName) {
        Page<MetroType> queryPage = new Page<>(page, pageSize);

        QueryWrapper<MetroType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CharSequenceUtil.isNotBlank(typeLevel), "type_level", typeLevel)
                .like(CharSequenceUtil.isNotBlank(typeName), "type_name", typeName);

        return Result.ok(metroTypeService.page(queryPage, queryWrapper));
    }

    @GetMapping()
    public Result get(@RequestParam String id) {
        return Result.ok(metroTypeService.getById(id));
    }

    @PostMapping()
    public Result add(@RequestBody MetroType type) {
        return Result.ok(metroTypeService.save(type));
    }

    @PutMapping()
    public Result update(@RequestParam String id, @RequestBody MetroType type) {
        type.setId(id);
        return Result.ok(metroTypeService.updateById(type));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String id) {
        return Result.ok(metroTypeService.removeById(id));
    }
}