package com.yupi.usercenter.common;


import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @author zhexueqi
 * @ClassName BaseResponse
 * @since 2024/3/6    18:44
 */
@Data
public class BaseResponse<T> implements Serializable {

    // 状态码
    private int code;
    // 返回信息
    private String message;
    // 返回数据
    private T data;
    // 返回描述
    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.description = "";
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.message = null;
        this.description= null;
        this.data = data;
    }

    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = null;
        this.description = errorCode.getDescription();
    }




}
