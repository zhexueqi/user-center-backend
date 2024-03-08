package com.yupi.usercenter.Exception;


import com.yupi.usercenter.common.ErrorCode;

import java.io.Serial;

/**
 * 业务异常
 * @author zhexueqi
 * @ClassName BusinessException
 * @since 2024/3/6    19:44
 */
public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1996911239629102910L;

    private final int code;

    private final String description;


    public BusinessException(int code, String message, String description)
    {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getMessage();
    }

    public BusinessException(ErrorCode errorCode,String description ){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
