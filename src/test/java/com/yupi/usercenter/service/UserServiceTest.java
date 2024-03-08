package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import jakarta.annotation.Resource;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author zhexueqi
 * @ClassName UserServiceTest
 * @since 2024/3/4    19:39
 */

/**
 * 用户服务测试
 *
 * @author zhexueqi
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("zhexueqi");
        user.setUserAccount("1234");
        user.setAvatarUrl("https://i1.hdslb.com/bfs/face/9ba9eb011c70a0868686f41b8e5e4e0aa5a531c6.jpg@240w_240h_1c_1s_!web-avatar-nav.avif");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("123");
        boolean result = userService.save(user);
        Assertions.assertTrue(result);


    }

    @Test
    void userRegister() {
        //测试密码为空
        String userAccount = "zhexueqi";
        String userPassword = "";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, "12345678");
        Assertions.assertEquals(-1,result);

        //检验账号长度
        userAccount = "zhe";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, "12345678");
        Assertions.assertEquals(-1,result);

        //检验是否有特殊字符
        userAccount = "zhe&xueqi";
        userPassword = "absdasd";
        result = userService.userRegister(userAccount, userPassword, checkPassword, "12345678");
        Assertions.assertEquals(-1,result);

        //检验密码长度是否不小于8位
        userAccount = "zhexueqi";
        userPassword = "1234567";
        result = userService.userRegister(userAccount, userPassword, checkPassword, "12345678");
        Assertions.assertEquals(-1,result);

        //检验账号重复
        userAccount = "zhexueqi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, "12345678");
        Assertions.assertEquals(-1,result);


        //注册
        userAccount = "yupi1";
        result = userService.userRegister(userAccount, userPassword, checkPassword, "12345678");
        Assertions.assertTrue(result>0);
    }
}