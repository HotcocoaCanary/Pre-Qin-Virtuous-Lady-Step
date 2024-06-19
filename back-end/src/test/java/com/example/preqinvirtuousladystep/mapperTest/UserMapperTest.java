package com.example.preqinvirtuousladystep.mapperTest;

import com.example.preqinvirtuousladystep.mapper.UserMapper;
import com.example.preqinvirtuousladystep.member.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        // 创建一个测试用户
        testUser = new User();
        testUser.setName("TestUser");
        testUser.setPassword("password123");
        testUser.setEmail("testuser@example.com");
        testUser.setPhoneNumber("1234567890");
        testUser.setAge(25);
        testUser.setGender("M");
        testUser.setRegistrationDate(LocalDate.now());
    }

    @Test
    void addWithEmail() {
        // 添加用户
        userMapper.addWithEmail(testUser.getEmail(), testUser.getPassword());

        // 根据邮箱查找用户
        User addedUser = userMapper.findByEmailOrPhoneNumber(testUser.getEmail());
        assertNotNull(addedUser, "用户应该被添加");
    }

    @Test
    void addWithPhoneNumber() {
        // 添加用户
        userMapper.addWithPhoneNumber(testUser.getPhoneNumber(), testUser.getPassword());

        // 根据电话号码查找用户
        User addedUser = userMapper.findByEmailOrPhoneNumber(testUser.getPhoneNumber());
        assertNotNull(addedUser, "用户应该被添加");
    }

    @Test
    void update() {
        // 添加用户
        userMapper.addWithEmail(testUser.getEmail(), testUser.getPassword());
        User addedUser = userMapper.findByEmailOrPhoneNumber(testUser.getEmail());

        // 更新用户信息
        addedUser.setName("UpdatedName");
        addedUser.setAge(30);
        userMapper.update(addedUser);

        // 查找更新后的用户
        User updatedUser = userMapper.findByEmailOrPhoneNumber(testUser.getEmail());
        assertEquals("UpdatedName", updatedUser.getName(), "Name should be updated");
        assertEquals(30, updatedUser.getAge(), "Age should be updated");
    }

    @Test
    void updatePassword() {
        // 添加用户
        userMapper.addWithEmail(testUser.getEmail(), testUser.getPassword());
        User addedUser = userMapper.findByEmailOrPhoneNumber(testUser.getEmail());

        // 更新用户密码
        String newPassword = "newpassword123";
        userMapper.updatePassword(newPassword, addedUser.getUserId());

        // 查找用户并验证密码
        User userWithNewPassword = userMapper.findByEmailOrPhoneNumber(testUser.getEmail());
        assertEquals(newPassword, userWithNewPassword.getPassword(), "密码应该被更新");
    }

    @Test
    void findByUserName() {
        // 添加用户
        userMapper.addWithEmail(testUser.getEmail(), testUser.getPassword());

        // 根据用户名查找用户
        User foundUser = userMapper.findByUserName(testUser.getName());
        assertNotNull(foundUser, "用户应该被找到");
    }

    @AfterEach
    void tearDown() {
        // 清理测试数据
        if (testUser.getUserId() != null) {
            userMapper.updatePassword(testUser.getPassword(), testUser.getUserId()); // 还原密码
            userMapper.update(testUser); // 还原用户信息
        }
    }
}
