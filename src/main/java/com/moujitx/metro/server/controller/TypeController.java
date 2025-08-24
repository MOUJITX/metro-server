package com.moujitx.metro.server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.Type;
import com.moujitx.metro.server.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @AuthAccess
    @GetMapping()
    public Result get(@RequestParam(defaultValue = "") String typeCode){
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(typeCode),"type_code", typeCode);
        return Result.ok(typeService.list(queryWrapper));
    }

    @PostMapping()
    public Result addOrUpdate(@RequestBody Type type) {
        return Result.created(typeService.saveOrUpdate(type));
    }

    @DeleteMapping()
    public Result delete(@RequestParam String typeCode) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", typeCode);
        return Result.ok(typeService.remove(queryWrapper));
    }
}
