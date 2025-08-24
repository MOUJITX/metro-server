package com.moujitx.metro.server.controller;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;
import com.moujitx.metro.server.service.StationService;
import com.moujitx.metro.server.service.StationVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    StationService stationService;

    @Autowired
    StationVoService stationVoService;

    @AuthAccess
    @GetMapping("/list")
    public Result list() {
        return Result.ok(stationVoService.list());
    }
}
