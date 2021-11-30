package com.weilong.community.exception;

public class BusinessException extends RuntimeException{
    private String message;
    private Integer code;
    public BusinessException(Integer code, String message) {
        super(message);
        this.message=message;
        this.code=code;
    }
    public BusinessException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
