package com.wind.auth.core.exception;

import lombok.Getter;

@Getter
public class PlatformException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private ErrorType errorType;

    /**
     * 默认是系统异常
     */
    public PlatformException() {
        this.errorType = ErrorType.SYSTEM_ERROR;
    }

    public PlatformException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public PlatformException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public PlatformException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
