package com.example.preqinvirtuousladystep.controller;

import com.example.preqinvirtuousladystep.member.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/run")
public class RunController {
    @PostMapping("/start")
    public Result<String> start() {
        return Result.success("aaaaa");
    }
}
