package com.moujitx.metro.server.controller;

import com.moujitx.metro.server.authorization.AuthAccess;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class WebController {

    @AuthAccess
    @GetMapping()
    public String load(HttpServletRequest request) {
        return "Hello world";
    }
}
