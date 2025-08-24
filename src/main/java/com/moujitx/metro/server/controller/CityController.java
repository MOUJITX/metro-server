package com.moujitx.metro.server.controller;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.entity.City;
import com.moujitx.metro.server.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;

    @AuthAccess
    @GetMapping("/list")
    public Result list() {
        return Result.ok(cityService.list());
    }
}
