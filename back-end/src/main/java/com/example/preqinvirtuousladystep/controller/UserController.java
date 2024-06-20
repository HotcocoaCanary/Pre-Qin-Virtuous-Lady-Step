package com.example.preqinvirtuousladystep.controller;

import com.example.preqinvirtuousladystep.member.Result;
import com.example.preqinvirtuousladystep.member.User;
import com.example.preqinvirtuousladystep.service.UserService;
import com.example.preqinvirtuousladystep.utils.JwtUtil;
import com.example.preqinvirtuousladystep.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<String> register(String EmailOrPhoneNumber,String password) {
        // 检查EmailOrPhoneNumber是否为null
        if (EmailOrPhoneNumber == null || EmailOrPhoneNumber.isEmpty()) {
            return Result.error("邮箱或手机号不能为空");
        }
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
    public Result<String> login(String EmailOrPhoneNumber, String password) {
        //根据用户名查询用户
        User loginUser = userService.findByEmailOrPhoneNumber(EmailOrPhoneNumber);
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确
        if (Md5Util.checkPassword(password,loginUser.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("UserID", loginUser.getUserId());
            claims.put("Name", loginUser.getName());
            return Result.success(jwtUtil.getToken(claims));
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        //根据用户名查询用户
        Map<String, Object> map = jwtUtil.parseToken(jwtUtil.getThisToken());
        String userName = (String) map.get("Name");
        User user = userService.findByUserName(userName);
        user.setPassword("热爱使我们进步");
        return Result.success(user);
    }
}
