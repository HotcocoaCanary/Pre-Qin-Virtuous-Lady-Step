package com.example.preqinvirtuousladystep.service.impl;

import com.example.preqinvirtuousladystep.mapper.UserMapper;
import com.example.preqinvirtuousladystep.member.User;
import com.example.preqinvirtuousladystep.service.UserService;
import com.example.preqinvirtuousladystep.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByEmailOrPhoneNumber(String EmailOrPhoneNumber) {
        return userMapper.findByEmailOrPhoneNumber(EmailOrPhoneNumber);
    }

    @Override
    public void register(String EmailOrPhoneNumber, String password) {
        String md5String = Md5Util.getMD5String(password);
        if (EmailOrPhoneNumber.contains("@")) {
            userMapper.addWithEmail(EmailOrPhoneNumber, md5String);
        } else {
            userMapper.addWithPhoneNumber(EmailOrPhoneNumber, md5String);
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePassword(String newPassword, int UserID) {
        userMapper.updatePassword(newPassword, UserID);
    }
}
