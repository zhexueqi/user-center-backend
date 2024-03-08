package com.yupi.usercenter.common;


/**
 * @author zhexueqi
 * @ClassName Result
 * @since 2024/3/6    14:15
 */

public class ResultUtils {

    public static<T>  BaseResponse<T> success(T data) {
        return new BaseResponse<T>(0, "ok", data);
    }


    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse(errorCode);
    }

    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse(code,message,null,description);
    }

    public static  BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse(errorCode.getCode(),null,message,description);
    }

    public static  BaseResponse error(ErrorCode errorCode,String description){
        return new BaseResponse(errorCode.getCode(),errorCode.getDescription(),description);
    }



}
