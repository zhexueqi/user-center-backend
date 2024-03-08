package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author zhexueqi
* @description 针对表【user】的数据库操作Service
* @createDate 2024-03-04 19:36:52
*/
public interface UserService extends IService<User> {


    /**
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafetyUser(User user);

    /**
     * 请求用户注销
     * @param request
     * @return
     */
    Integer userLogOut(HttpServletRequest request);
}
