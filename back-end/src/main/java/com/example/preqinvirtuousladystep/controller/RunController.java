package com.example.preqinvirtuousladystep.controller;

import com.example.preqinvirtuousladystep.member.Result;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/run")
@Validated
public class RunController {
    @PostMapping("/start")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String EmailOrPhoneNumber, @Pattern(regexp = "^\\S{5,16}$") String password) {
        return Result.success("aaaaa");
    }
}
