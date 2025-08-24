package com.moujitx.metro.server.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/")
public class WebController {

    @GetMapping()
    public String load(HttpServletRequest request) {
        return "Hello world";
    }
}
