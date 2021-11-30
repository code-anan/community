package com.weilong.community.dto;

import com.weilong.community.exception.BusinessException;

public class ResultDTO {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public static ResultDTO error(BusinessException e){
        return new ResultDTO(e.getCode(),e.getMessage());
    }
}
