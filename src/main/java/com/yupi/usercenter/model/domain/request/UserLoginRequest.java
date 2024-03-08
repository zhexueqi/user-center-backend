package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhexueqi
 * @ClassName UserRegisterRequest
 * @since 2024/3/5    10:18
 */
@Data
public class UserLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4825782408966262658L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */

    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}
