package com.yupi.usercenter.common;


/**
 * @author zhexueqi
 * @ClassName ErrorCode
 * @since 2024/3/6    19:20
 */
public enum ErrorCode {


    PARAMS_ERROR(40000, "参数错误", ""),
    NULL_ERROR(40001,"请求数据为空",""),
    NO_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常",""),
    ;

    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
