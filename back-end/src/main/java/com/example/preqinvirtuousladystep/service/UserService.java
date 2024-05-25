package com.example.preqinvirtuousladystep.service;

import com.example.preqinvirtuousladystep.member.User;

public interface UserService {
    //根据用户名查询用户
    User findByEmailOrPhoneNumber(String EmailOrPhoneNumber);

    //注册
    void register(String EmailOrPhoneNumber, String password);

    //更新
    void update(User user);

    //更新密码
    void updatePassword(String newPassword, int UserID);
}
