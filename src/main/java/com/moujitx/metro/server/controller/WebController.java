package com.moujitx.metro.server.controller;

import com.moujitx.metro.server.authorization.AuthAccess;
import com.moujitx.metro.server.common.Result;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping()
public class WebController {

    @AuthAccess
    @GetMapping("/")
    public String load(HttpServletRequest request) {
        return "Hello world";
    }

    @AuthAccess
    @GetMapping("/dashboard")
    public Result dashboard() {
        return Result.ok();
    }

}
