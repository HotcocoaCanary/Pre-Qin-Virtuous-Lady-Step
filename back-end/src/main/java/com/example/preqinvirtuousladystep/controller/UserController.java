package com.example.preqinvirtuousladystep.controller;

import com.example.preqinvirtuousladystep.member.Result;
import com.example.preqinvirtuousladystep.member.User;
import com.example.preqinvirtuousladystep.service.UserService;
import com.example.preqinvirtuousladystep.utils.JwtUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String EmailOrPhoneNumber, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByEmailOrPhoneNumber(EmailOrPhoneNumber);
        if (u == null) {
            //没有占用
            //注册
            userService.register(EmailOrPhoneNumber, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String EmailOrPhoneNumber, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByEmailOrPhoneNumber(EmailOrPhoneNumber);
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确
        if (password.equals(loginUser.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("UserID", loginUser.getUserId());
            claims.put("Name", loginUser.getName());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

}
