package com.example.dongruan.control;

public class SysException extends Exception{
    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    private Integer Code;

    public SysException(Integer code) {
        Code = code;
    }

    public SysException(String message, Integer code) {
        super(message);
        Code = code;
    }

    public SysException(String message, Throwable cause, Integer code) {
        super(message, cause);
        Code = code;
    }

    public SysException(Throwable cause, Integer code) {
        super(cause);
        Code = code;
    }

    public SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        Code = code;
    }
}
