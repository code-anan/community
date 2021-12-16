package com.weilong.community.enums;

public enum NotificationEnum {
    NOTIFICATION_QUESTION(1,"回复了我的问题"),
    NOTIFICATION_COMMENT(2,"回复了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    NotificationEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String getNameByTypeId(Integer id){
       if(id==NotificationEnum.NOTIFICATION_QUESTION.getType()){
           return NotificationEnum.NOTIFICATION_QUESTION.getName();
       }else{
           return NotificationEnum.NOTIFICATION_COMMENT.getName();
       }
    }
}
