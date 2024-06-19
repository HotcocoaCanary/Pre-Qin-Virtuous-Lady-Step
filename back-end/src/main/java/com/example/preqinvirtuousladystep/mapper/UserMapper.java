package com.example.preqinvirtuousladystep.mapper;

import com.example.preqinvirtuousladystep.member.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 根据邮箱或电话号查找用户
    @Select("SELECT * FROM user WHERE Email = #{Identifier} OR PhoneNumber = #{Identifier}")
    User findByEmailOrPhoneNumber(@Param("Identifier") String identifier);

    // 邮箱+密码添加用户
    @Insert("insert into user(Name, Email, Password, RegistrationDate) " + "values(SUBSTRING_INDEX(#{email}, '@', 1), #{email}, #{password}, now())")
    void addWithEmail(String email, String password);

    @Insert("insert into user(Name,PhoneNumber,Password,RegistrationDate) " + "values(#{phoneNumber},#{phoneNumber},#{password},now())")
    void addWithPhoneNumber(String phoneNumber, String password);

    // 更新用户信息
    @Update("UPDATE user SET Name = #{name}, Age = #{age}, Gender = #{gender}, Email = #{email}, PhoneNumber = #{phoneNumber}, RegistrationDate = now() WHERE UserID = #{userId}")
    void update(User user);

    // 更新用户密码
    @Update("UPDATE user SET Password = #{newPassword}, RegistrationDate = now() WHERE UserID = #{UserID}")
    void updatePassword(@Param("newPassword") String newPassword, @Param("UserID") Integer UserID);

    @Select("SELECT * FROM user WHERE Name = #{userName}")
    User findByUserName(@Param("userName") String userName);
}
