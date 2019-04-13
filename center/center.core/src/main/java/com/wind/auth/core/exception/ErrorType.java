package com.wind.auth.core.exception;

import lombok.Getter;

@Getter
public enum ErrorType{

    SYSTEM_ERROR(10000, "系统异常"),
    SYSTEM_BUSY(10001, "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE(20001, "服务未找到"),
    GATEWAY_ERROR(20002, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(20003, "网关超时"),

    ARGUMENT_NOT_VALID(30001, "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT(30002, "上传文件大小超过限制");

    /**
     * 错误类型码
     */
    private Integer code;
    /**
     * 错误类型描述信息
     */
    private String message;

    ErrorType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
