package com.yupi.usercenter.model.domain.request;

import lombok.Data;
import java.io.Serializable;

/**
 * @author zhexueqi
 * @ClassName UserRegisterRequest
 * @since 2024/3/5    10:18
 */
@Data
public class UserRegisterRequest  implements Serializable {


    private static final long serialVersionUID = -7360266844878619800L;
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

    /**
     * 星球编号
     */
    private String planetCode;
}
