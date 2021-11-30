package com.weilong.community.enums;

public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer id;
    CommentTypeEnum(Integer id) {
        this.id=id;
    }
    public Integer getType(){
        return id;
    }
}
