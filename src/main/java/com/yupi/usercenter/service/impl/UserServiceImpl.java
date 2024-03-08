package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.Exception.BusinessException;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.service.UserService;
import com.yupi.usercenter.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yupi.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author zhexueqi
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-04 19:36:52
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    final String sALT = "zhexueqi";

    @Resource
    private UserMapper userMapper;



    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {
        //校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号过短");
        }
        if (userPassword.length()<8|| checkPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户密码过短");
        }
        if (planetCode.length()>5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号过长");
        }

        //账号不能包含特殊字符
        String VaildPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(VaildPattern).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }
        //密码和校验密码相同
        if (!userPassword.equals(checkPassword)){
            return -1;
        }

        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count>0){
            return -1;
        }

        //星球编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("planetCode", planetCode);
        count = userMapper.selectCount(queryWrapper);
        if (count>0){
            return -1;
        }

        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((sALT + userPassword).getBytes());
        //3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        int saveResult = userMapper.insert(user);
        if (saveResult == 0){
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //校验
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        if (userAccount.length()<4){
            return null;
        }
        if (userPassword.length()<8){
            return null;
        }

        //账号不能包含特殊字符
        String VaildPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(VaildPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
        }

        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((sALT + userPassword).getBytes());

        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        //用户不存在
        if (user == null){
            log.info("user login failed , userAccount cannot match userPassword");
            return null;
        }
        //3.脱敏
        User safetyUser = getSafetyUser(user);

        //4.记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);

        return safetyUser;
    }

    @Override
    public User getSafetyUser(User user){
        if (user == null){
            return null;
        }
        User SafetyUser = new User();
        SafetyUser.setId(user.getId());
        SafetyUser.setUserAccount(user.getUserAccount());
        SafetyUser.setEmail(user.getEmail());
        SafetyUser.setUsername(user.getUsername());
        SafetyUser.setUpdateTime(user.getUpdateTime());
        SafetyUser.setUserRole(user.getUserRole());
        SafetyUser.setPlanetCode(user.getPlanetCode());
        SafetyUser.setPhone(user.getPhone());
        SafetyUser.setAvatarUrl(user.getAvatarUrl());
        SafetyUser.setUserStatus(user.getUserStatus());
        return SafetyUser;
    }

    /**
     * 请求用户注销
     * @param request
     * @return
     */
    public Integer userLogOut(HttpServletRequest request) {
        //移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




